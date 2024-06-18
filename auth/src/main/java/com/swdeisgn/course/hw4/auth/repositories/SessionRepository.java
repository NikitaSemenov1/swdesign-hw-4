package com.swdeisgn.course.hw4.auth.repositories;

import org.springframework.data.repository.CrudRepository;
import com.swdeisgn.course.hw4.auth.entities.SessionEntity;

import java.util.UUID;

public interface SessionRepository extends CrudRepository<SessionEntity, UUID> {
}
