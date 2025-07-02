package com.springboot.autowiki.service;

import com.springboot.autowiki.exception.ElectricCarWithEngineDisplacementException;
import com.springboot.autowiki.exception.RegularCarWithoutEngineDisplacementException;
import com.springboot.autowiki.model.Article;
import com.springboot.autowiki.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ArticleService {

private final ArticleRepository articleRepository;
private final MailService mailService;

public ArticleService(ArticleRepository articleRepository, MailService mailService) {
    this.articleRepository = articleRepository;
    this.mailService = mailService;
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


}
