package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.models.Language;
import com.itis.practice.team123.cvproject.repositories.LanguageRepository;
import com.itis.practice.team123.cvproject.services.interfaces.LanguageService;
import org.springframework.stereotype.Service;

@Service
public class LanguageServiceImpl implements LanguageService {
    private LanguageRepository languageRepository;

    public LanguageServiceImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public Language initializeLanguage(Language language) {
        Language resultLanguage = languageRepository.findByLevelAndLanguageIgnoreCase(language.getLevel(), language.getLanguage()).orElse(null);
        if(resultLanguage == null) {
            language.setId(null);
            resultLanguage = languageRepository.save(language);
        }
        return resultLanguage;
    }

    @Override
    public Language getLanguage(Long language) {
        return languageRepository.findById(language).orElseThrow(IllegalStateException::new);
    }
}