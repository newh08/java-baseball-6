package baseball.domain.result;

import baseball.domain.result.resultnum.BallNum;
import baseball.domain.result.resultnum.ResultNum;
import baseball.domain.result.resultnum.StrikeNum;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GameResult {
    private static final String DELIMITER = " ";
    private static final String NOTHING = "낫띵";
    private final List<ResultNum> resultNumbers;

    public GameResult() {
        resultNumbers = new ArrayList<>();
        resultNumbers.add(new StrikeNum());
        resultNumbers.add(new BallNum());
    }

    public void update(BallResult ballResult) {
        resultNumbers.replaceAll(resultNum -> resultNum.update(ballResult));
    }

    public String getResult() {
        String result = resultNumbers.stream().map(ResultNum::getResult)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.joining(DELIMITER));
        if (result.isEmpty()) {
            result = NOTHING;
        }
        return result;
    }
}