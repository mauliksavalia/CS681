package edu.umb.cs681.hw03;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CarTest {

    private static ArrayList<Car> usedCarsData = new ArrayList<>();

	@BeforeAll
    public static void setUp(){
		    usedCarsData = new TestFixtureInitializer().CarList();
	    };


        @Test
        public void averagePrice() {
    
            double averagePrice = usedCarsData.stream().map(Car::getPrice)
                    .reduce(new CarPriceResultHolder(), (result, price) -> {
                        result.setAverage((result.getNumCarExamined() * result.getAverage() + price)
                                / (result.getNumCarExamined() + 1));
                        result.setNumCarExamined(result.getNumCarExamined() + 1);
                        return result;
                    }, (finalResult, intermediateResult) -> finalResult).getAverage();
    
            double avg = 59666.666666666664;
    
            assertEquals(avg, averagePrice);
        }
    }