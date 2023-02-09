package racingcar.controller;

import java.util.List;

import racingcar.domain.Car;
import racingcar.repository.CarRepository;
import racingcar.service.CarService;
import racingcar.service.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class CarController {
	private CarRepository carRepository = new CarRepository();
	int roundCount;

	public void run() {
		while (!getCarNames()) ;
		while (!getRoundCount()) ;
		move(roundCount);
	}

	private boolean getCarNames() {
		try {
			OutputView.printCarNameRequestMsg();
			List<String> carNames = InputView.readCarNames();
			carNames.stream().forEach(carName -> carRepository.add(new Car(carName)));
			return true;
		} catch (Exception e) {
			carRepository.clear();
			System.out.println(e.getMessage());
			return false;
		}
	}

	private boolean getRoundCount() {
		try {
			OutputView.printRoundCountRequestMsg();
			roundCount = InputView.readRoundCount();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	private void move(int roundCount) {
		CarService carService = new CarService(new RandomNumberGenerator());
		OutputView.printOutputMsg();
		OutputView.printRacingState(carService.getPositionToString());
		for (int i = 0; i < roundCount; i++) {
			carService.moveCars();
			OutputView.printRacingState(carService.getPositionToString());
		}
		OutputView.printRacingResult(carService.getWinners());
	}
}
