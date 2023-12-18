package com.clubs.aftas.services.implementations;

import com.clubs.aftas.dtos.FilterDTO;
import com.clubs.aftas.dtos.huntings.requests.HuntingRequest;
import com.clubs.aftas.entities.Competition;
import com.clubs.aftas.entities.Fish;
import com.clubs.aftas.entities.Hunting;
import com.clubs.aftas.entities.Member;
import com.clubs.aftas.handlingExceptions.costumExceptions.DoNotExistException;
import com.clubs.aftas.handlingExceptions.costumExceptions.EmptyException;
import com.clubs.aftas.handlingExceptions.costumExceptions.ValidationException;
import com.clubs.aftas.repositories.HuntingRepository;
import com.clubs.aftas.services.*;
import com.clubs.aftas.services.validations.ValidationHuntingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service


public class HuntingServiceImpl extends BaseService<Hunting, Long> implements HuntingService {

    private final HuntingRepository huntingRepository;

    private final FishService fishService;

    private final ValidationHuntingService validation;

    private final CompetitionService competitionService;

    private final RankingService rankingService;

    private final MemberService memberService;

    public HuntingServiceImpl(HuntingRepository huntingRepository, FishService fishService , ValidationHuntingService validation, CompetitionService competitionService, RankingService rankingService, MemberService memberService) {
        super(huntingRepository , Hunting.class);
        this.huntingRepository = huntingRepository;
        this.validation = validation;
        this.fishService = fishService;
        this.competitionService = competitionService;
        this.rankingService = rankingService;
        this.memberService = memberService;
    }
    @Override
    public List<Hunting> getAllHuntings() {
        return getAllEntities();
    }

    @Override
    public Page<Hunting> getAllHuntingsWithPagination(Pageable pageable) {
        return getAllEntitiesWithPagination(pageable);
    }

    @Override
    public Hunting getHuntingById(Long id) {
        return getEntityById(id);
    }

    @Override
    public void addHunting(HuntingRequest huntingRequest){

        Competition competition = competitionService.getCompetitionById(huntingRequest.getCompetitionId());

        Member member = memberService.getMemberById(huntingRequest.getMemberId());

        Fish huntedFish = fishService.getFishById(huntingRequest.getFishId());

        Optional<Hunting> hunting = huntingRepository.findByCompetitionAndMemberAndFish(competition, member, huntedFish);


        // Check if the date of competition has passed
        if (competition.getDate().isBefore(LocalDate.now())) {
            throw new ValidationException("The date of the competition has passed");
        }

        // Check if the user has been not  registred to the competition
        if(hunting.isEmpty() && !rankingService.checkIfMemberIsRegisteredInCompetition(member, competition)){
            throw new ValidationException("The member is not registred in the competition: " + competition.getCode());
        }

        if(hunting.isEmpty() ){
            huntingRepository.save(buildHuntingObject(1, competition, member, huntedFish, null));
            return;
        }

        if(!validation.checkIfHuntedFishIsValid(huntedFish ,huntingRequest.getWeightOfHuntedFish())){
           throw new ValidationException("weight of hunted fish is not higher then the average weight of the fish");
        }



        hunting.get().setNumberOfFish(hunting.get().getNumberOfFish() + 1);

        huntingRepository.save(hunting.get());

    }


    @Override
    public void decreaseHunting(Long huntingId){

        Optional<Hunting> hunting = huntingRepository.findById(huntingId);

        if(hunting.isEmpty()){
            throw new DoNotExistException("Hunting not found");
        }

        if(hunting.get().getNumberOfFish() == 1){
            huntingRepository.delete(hunting.get());
            return;
        }

        hunting.get().setNumberOfFish(hunting.get().getNumberOfFish() - 1);

        huntingRepository.save(hunting.get());
    }

    @Override
    public List<Hunting> searchHuntingsByCriteria(List<FilterDTO> filters) {
        return Optional.of(huntingRepository.findAll(searchByCriteria(filters)))
                .orElseThrow(() -> new EmptyException("No hunting has been found"));
    }



    @Override
    public List<Hunting> searchHuntings(String value) {
        return Optional.of(huntingRepository.findAll(search(value)))
                .orElseThrow(() -> new EmptyException("No hunting has been found"));
    }

    public Hunting buildHuntingObject(Integer numberOfFish, Competition competition, Member member, Fish huntedFish, Long huntingId){

        return Hunting.builder()
                .id(huntingId)
                .numberOfFish(numberOfFish)
                .fish(huntedFish)
                .competition(competition)
                .member(member)
                .build();

    }
}
