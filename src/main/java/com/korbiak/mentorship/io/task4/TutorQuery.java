package com.korbiak.mentorship.io.task4;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TutorQuery {
    static final String SELECT_ALL = "SELECT * FROM tutor";
    static final String SELECT_BY_ID = "SELECT * FROM tutor WHERE id = ?";
    static final String INSERT = "INSERT INTO public.tutor(id, name, surname) VALUES (?, ?, ?)";
    static final String UPDATE = "UPDATE public.tutor SET name=?, surname=? WHERE id=?";
    static final String DELETE = "DELETE FROM public.tutor WHERE id=?";
}
