package com.epam.KaterynaKravchenko.repos;

import com.epam.KaterynaKravchenko.entity.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {

    List<Message> findByTag(String tag);

}
