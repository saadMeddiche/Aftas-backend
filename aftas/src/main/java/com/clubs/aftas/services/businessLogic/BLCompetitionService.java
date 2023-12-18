package com.clubs.aftas.services.businessLogic;

import com.clubs.aftas.dtos.competition.Top;
import com.clubs.aftas.entities.Competition;
import com.clubs.aftas.entities.Fish;
import com.clubs.aftas.entities.Ranking;
import com.clubs.aftas.repositories.RankingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class BLCompetitionService {

    private final RankingRepository rankingRepository;

    public String createCodeForCompetition(String location , LocalDate date) {

        // Get First Three Letter of location And Make It Lower Case
        String firstThreeLetters = location.substring(0, 3).toLowerCase();

        // Get The two Numberss Of Year Of Date
        int year = date.getYear() % 100;

        // Get The Month Of Date
        int month = date.getMonthValue();

        // Get The Day Of Date
        int day = date.getDayOfMonth();

        // Concatenate All To Create The Code
       StringBuilder stringBuilder = new StringBuilder();
       stringBuilder.append(firstThreeLetters);
       stringBuilder.append("-");
       stringBuilder.append(day);
       stringBuilder.append("-");
       stringBuilder.append(month);
       stringBuilder.append("-");
       stringBuilder.append(year);

       return stringBuilder.toString();

    }

    public void resultsOfCompetition(Competition competition) {

        Map<Long, Integer> result = calculateResultOfParticipation(competition);

        updateRankings(competition, result);

        List<Ranking> rankings = rankingRepository.findByCompetitionOrderByScoreDesc(competition);

        makingRanks(rankings);

        rankingRepository.saveAll(rankings);
    }

    private void makingRanks(List<Ranking> rankings) {
        int rank = 1;
        int previousScore = 999999999;

        for (Ranking ranking : rankings) {
            int currentScore = ranking.getScore();

            if (currentScore < previousScore) {
                ranking.setRank(rank);
            }else if(currentScore == previousScore){
                ranking.setRank(rank - 1);
                continue;
            }

            rank++;
            previousScore = currentScore;
        }
    }




    // Long : Member Id | Integer : Points
    public Map<Long, Integer> calculateResultOfParticipation(Competition competition) {
        return competition.getHuntings().stream()
                .collect(Collectors.groupingBy(
                        hunting -> hunting.getMember().getId(),
                        Collectors.summingInt(hunting -> {
                            Fish fish = hunting.getFish();
                            int points = fish.getLevel().getPoints();
                            int numberOfFish = hunting.getNumberOfFish();
                            return points * numberOfFish;
                        })
                ));
    }

    public void updateRankings(Competition competition , Map<Long, Integer> result) {

        List<Ranking> updatedRankings = competition.getRankings().stream()
                .map(ranking -> {
                    Long memberId = ranking.getMember().getId();
                    // Why Default , in case if the member didn't catch any fish (no huntings)
                    Integer newScore = result.getOrDefault(memberId , 0);
                    ranking.setScore(newScore);
                    return ranking;
                })
                .collect(Collectors.toList());

        rankingRepository.saveAll(updatedRankings);
    }

    public Map<Integer, List<Ranking>> groupRankingsByScore(List<Ranking> rankingList) {
        return rankingList.stream()
                .collect(Collectors.groupingBy(Ranking::getScore));
    }

    public List<Integer> getTopThreeScores(List<Ranking> rankingList) {
        return rankingList.stream()
                .map(Ranking::getScore)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Top>> createTopThreeMap(Map<Integer, List<Ranking>> rankingsByScore, List<Integer> topThreeScores) {
        Map<Integer, List<Top>> topThree = new HashMap<>();
        int rank = 1;

        for (Integer score : topThreeScores) {
            List<Ranking> rankingsWithScore = rankingsByScore.get(score);
            List<Top> topList = createTopList(rankingsWithScore);
            topThree.put(rank++, topList);
        }

        return topThree;
    }

    public List<Top> createTopList(List<Ranking> rankingsWithScore) {
        return rankingsWithScore.stream()
                .map(ranking -> new Top(ranking.getMember(), ranking.getScore()))
                .collect(Collectors.toList());
    }
}
