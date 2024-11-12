package com.junlinc.rest.service;
import com.junlinc.rest.domain.Title;
import com.junlinc.rest.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TitleService {

    private final TitleRepository titleRepository;
    @Autowired
    public TitleService(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }
    public Title getTitleById(String id){
        return titleRepository.findById(id).orElseThrow(()-> new RuntimeException("Title not found"+id));
    }
    public Title updateTitle(String id, String newTitle){
        Title title = getTitleById(id);
        title.setTitle(newTitle);
        return titleRepository.save(title);
    }



}
