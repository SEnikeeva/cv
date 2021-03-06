package com.itis.practice.team123.cvproject.services.interfaces;

import com.itis.practice.team123.cvproject.models.Language;
import org.springframework.data.util.Pair;

import java.util.List;

public interface LanguageService {
    Language initializeLanguage(Language language);

    Language getLanguage(Long language);

    Language getLanguageByNameAndLevel(String lang);

    List<Language> getAllLanguages();

    Pair<Boolean, Boolean> checkAndRemoveIfHasTheSameLanguageWithAnotherLevel(List<Language> languages, Language language);
}
