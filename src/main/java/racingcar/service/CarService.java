package racingcar.service;

import java.util.List;

import racingcar.domain.Car;
import racingcar.repository.CarRepository;

public class CarService {

	RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

	public void moveCars() {
		List<Car> cars = CarRepository.getCars();
		for (Car car : cars) {
			car.move(randomNumberGenerator.getNumber());
		}
	}
}
