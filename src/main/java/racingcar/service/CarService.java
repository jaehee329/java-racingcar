package racingcar.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import racingcar.domain.Car;
import racingcar.repository.CarRepository;

public class CarService {
	private final List<Car> cars = CarRepository.getCars();
	private final NumberGenerator numberGenerator;

	public CarService(final NumberGenerator numberGenerator) {
		this.numberGenerator = numberGenerator;
	}

	public void moveCars() {
		cars.stream().forEach(car -> car.move(numberGenerator.getNumber()));
	}

	public List<String> getWinners() {
		int maxPosition = cars.stream()
			.max(Comparator.comparingInt(Car::getPosition)).get()
			.getPosition();

		return cars.stream()
			.filter(car -> car.getPosition() == maxPosition)
			.map(car -> car.getName())
			.collect(Collectors.toList());
	}

	public String getPositionToString() {
		return cars.stream()
			.map(car -> car.printCarNameWithPosition())
			.collect(Collectors.joining("\n"));
	}
}
