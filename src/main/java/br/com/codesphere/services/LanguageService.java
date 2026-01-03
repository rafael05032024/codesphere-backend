package br.com.codesphere.services;

import java.util.ArrayList;
import java.util.List;

import br.com.codesphere.dtos.LanguageDTO;
import br.com.codesphere.entities.LanguageEntity;
import br.com.codesphere.repositories.LanguageRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class LanguageService {

  @Inject
  LanguageRepository languageRepository;

  public List<LanguageDTO> list() {
    List<LanguageEntity> languages = languageRepository.listAll();
    List<LanguageDTO> list = new ArrayList<>();

    languages.forEach((lang) -> {
      list.add(new LanguageDTO(lang.id, lang.name, lang.template));
    });

    return list;
  }

}
