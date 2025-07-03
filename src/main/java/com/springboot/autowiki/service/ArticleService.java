package com.springboot.autowiki.service;

import com.springboot.autowiki.exception.ElectricCarWithEngineDisplacementException;
import com.springboot.autowiki.exception.RegularCarWithoutEngineDisplacementException;
import com.springboot.autowiki.model.Article;
import com.springboot.autowiki.model.Car;
import com.springboot.autowiki.repository.ArticleRepository;
import com.springboot.autowiki.repository.CarRepository;
import com.springboot.autowiki.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ArticleService {

private final ArticleRepository articleRepository;
private final MailService mailService;
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    public ArticleService(ArticleRepository articleRepository, MailService mailService, CarRepository carRepository, UserRepository userRepository) {
    this.articleRepository = articleRepository;
    this.mailService = mailService;
    this.carRepository = carRepository;
    this.userRepository = userRepository;
    }

public Article createArticle(Long carId, String createdBy, String proposedManufacturer, String proposedModel,
                             String proposedGearType, String proposedFuelType, Integer proposedEngineDisplacement,
                             Integer proposedEnginePower, Integer proposedEngineTorque, Integer proposedProductionStartYear,
                             Integer proposedProductionEndYear, Integer proposedPrice, Integer proposedNumberOfSeats,
                             String proposedBodyType, Integer proposedNumberOfDoors, Integer proposedLength, Integer proposedWidth,
                             Integer proposedHeight, Integer proposedWheelbase, Integer proposedWeight,
                             BigDecimal proposedAccelerationToHundred, Integer proposedTopSpeed, String proposedDriveWheelsConfiguration,
                             BigDecimal proposedWeightPerHP, String proposedImageUrl) {

    if (!"Electric".equals(proposedFuelType) && proposedEngineDisplacement == null) {
        throw new RegularCarWithoutEngineDisplacementException("The engine displacement cannot be null for non electric cars.");
    } else if ("Electric".equals(proposedFuelType) && proposedEngineDisplacement != null) {
        throw new ElectricCarWithEngineDisplacementException("Invalid engine displacement value for an electric car.");
    }
    else if (proposedFuelType.equals("Electric") && (proposedEngineDisplacement != null)) {
        throw new ElectricCarWithEngineDisplacementException("Invalid engine displacement value for an electric car.");
    }

    Article article = new Article();

    article.setCarId(carId);
    article.setCreatedBy(createdBy);
    article.setProposedManufacturer(proposedManufacturer);
    article.setProposedModel(proposedModel);
    article.setProposedGearType(proposedGearType);
    article.setProposedFuelType(proposedFuelType);
    article.setProposedEngineDisplacement(proposedEngineDisplacement);
    article.setProposedEnginePower(proposedEnginePower);
    article.setProposedEngineTorque(proposedEngineTorque);
    article.setProposedProductionStartYear(proposedProductionStartYear);
    article.setProposedProductionEndYear(proposedProductionEndYear);
    article.setProposedPrice(proposedPrice);
    article.setProposedNumberOfSeats(proposedNumberOfSeats);
    article.setProposedBodyType(proposedBodyType);
    article.setProposedNumberOfDoors(proposedNumberOfDoors);
    article.setProposedLength(proposedLength);
    article.setProposedWidth(proposedWidth);
    article.setProposedHeight(proposedHeight);
    article.setProposedWheelbase(proposedWheelbase);
    article.setProposedWeight(proposedWeight);
    article.setProposedAccelerationToHundred(proposedAccelerationToHundred);
    article.setProposedTopSpeed(proposedTopSpeed);
    article.setProposedDriveWheelsConfiguration(proposedDriveWheelsConfiguration);
    article.setProposedWeightPerHP(proposedWeightPerHP);
    article.setProposedImageUrl(proposedImageUrl);

    String approvalToken = mailService.generateApprovalToken();
    String denialToken = mailService.generateDenialToken();
    article.setApprovalToken(approvalToken);
    article.setDenialToken(denialToken);

    mailService.sendArticleApprovalLink(article);
    Article savedArticle = articleRepository.save(article);

    return savedArticle;
}

public boolean approveArticle(String token) {
    Optional<Article> optionalArticle = articleRepository.findByApprovalToken(token);

    if (optionalArticle.isEmpty()) {
        return false;
    }

    Article article = optionalArticle.get();
    article.setApproved(true);
    article.setApprovalToken(null);
    article.setDenialToken(null);
    articleRepository.save(article);

    Optional<Car> optionalCar = carRepository.findById(article.getCarId());
    Car car = optionalCar.get();
    car.setManufacturer(article.getProposedManufacturer());
    car.setModel(article.getProposedModel());
    car.setGearType(article.getProposedGearType());
    car.setFuelType(article.getProposedFuelType());
    car.setEngineDisplacement(article.getProposedEngineDisplacement());
    car.setEnginePower(article.getProposedEnginePower());
    car.setEngineTorque(article.getProposedEngineTorque());
    car.setProductionStartYear(article.getProposedProductionStartYear());
    car.setProductionEndYear(article.getProposedProductionEndYear());
    car.setPrice(article.getProposedPrice());
    car.setNumberOfSeats(article.getProposedNumberOfSeats());
    car.setNumberOfDoors(article.getProposedNumberOfDoors());
    car.setLength(article.getProposedLength());
    car.setWidth(article.getProposedWidth());
    car.setHeight(article.getProposedHeight());
    car.setWheelbase(article.getProposedWheelbase());
    car.setWeight(article.getProposedWeight());
    car.setAccelerationToHundred(article.getProposedAccelerationToHundred());
    car.setTopSpeed(article.getProposedTopSpeed());
    car.setDriveWheelsConfiguration(article.getProposedDriveWheelsConfiguration());
    car.setWeightPerHP(article.getProposedWeightPerHP());
    car.setImageUrl(article.getProposedImageUrl());

    mailService.sendChangeApprovalEmail(userRepository.findByEmail(article.getCreatedBy()).get());
    carRepository.save(car);

    return true;
}

    public boolean denyArticle(String token) {
        Optional<Article> optionalArticle = articleRepository.findByDenialToken(token);

        if (optionalArticle.isEmpty()) {
            return false;
        }

        articleRepository.delete(optionalArticle.get());
        return true;
    }

}
