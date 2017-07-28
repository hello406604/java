package com.kaishengit.crm.service;

import com.kaishengit.crm.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> findAllByAccountId(Integer id, boolean showAll);
}
