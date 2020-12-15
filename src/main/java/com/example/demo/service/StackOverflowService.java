package com.example.demo.service;

import com.example.demo.model.SiteDTO;
import com.example.demo.model.StackOverflowWebSite;
import com.example.demo.persistence.StackOverflowWebsiteRepository;
import com.google.common.collect.ImmutableList;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
public class StackOverflowService {
    @Autowired
    private StackOverflowWebsiteRepository stackOverflowWebsiteRepository;
    @Autowired
    private StackExchangeClient client;

    public List<StackOverflowWebSite> findAll(){
        return client.getSites().stream().map(this::DTOtoWebsite)
                .filter(this::ignoreMeta).collect(collectingAndThen(toList(), ImmutableList::copyOf));
    }

    private boolean ignoreMeta(StackOverflowWebSite stackOverflowWebSite) {
        return !stackOverflowWebSite.getWebsite().contains("meta");
    }

    public StackOverflowWebSite DTOtoWebsite(@NotNull SiteDTO dto){
        return new StackOverflowWebSite(dto.getName(), dto.getSite_url().substring(8, dto.getSite_url().length() - 4),
                dto.getFavicon_url(), dto.getName(), dto.getAudience());
    }

   /* public List<StackOverflowWebSite> findAll() {
        return stackOverflowWebsiteRepository.findAll();
    }

   @PostConstruct
    public void init(){
       stackOverflowWebsiteRepository.saveAll(items);
    }*/
}
