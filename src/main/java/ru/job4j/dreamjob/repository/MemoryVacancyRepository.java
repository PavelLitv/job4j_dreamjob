package ru.job4j.dreamjob.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Vacancy;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryVacancyRepository implements VacancyRepository {
    private int nextId = 1;
    private final Map<Integer, Vacancy> vacancies = new HashMap<>();

    private MemoryVacancyRepository() {
        save(new Vacancy(0, "Intern Java Developer", "Стажер на частичную занятость", LocalDateTime.now()));
        save(new Vacancy(0, "Junior Java Developer", "1 год опыта, требования: java core, ООП, SOLID", LocalDateTime.now().minusMinutes(1_000)));
        save(new Vacancy(0, "Junior+ Java Developer", "2 года опыта, требования Spring/Spring boot", LocalDateTime.now().minusMinutes(5_000)));
        save(new Vacancy(0, "Middle Java Developer", "3 года опыта, уверенные знания Spring/Spring boot, Hibernate", LocalDateTime.now().minusMinutes(9_999)));
        save(new Vacancy(0, "Middle+ Java Developer", "3+ года опыта, уверенные знания Spring/Spring boot, Hibernate, контейнеризация", LocalDateTime.now().minusMinutes(15_000)));
        save(new Vacancy(0, "Senior Java Developer", "6+ лет опыта, опыт высоко нагруженных систем", LocalDateTime.now().minusMinutes(19_999)));
    }

    @Override
    public Vacancy save(Vacancy vacancy) {
        vacancy.setId(nextId++);
        vacancies.put(vacancy.getId(), vacancy);
        return vacancy;
    }

    @Override
    public boolean deleteById(int id) {
        return vacancies.remove(id) != null;
    }

    @Override
    public boolean update(Vacancy vacancy) {
        return vacancies.computeIfPresent(vacancy.getId(),
                (id, oldVacancy) -> new Vacancy(
                        oldVacancy.getId(),
                        vacancy.getTitle(),
                        vacancy.getDescription(),
                        vacancy.getCreationDate())
        ) != null;
    }

    @Override
    public Optional<Vacancy> findById(int id) {
        return Optional.ofNullable(vacancies.get(id));
    }

    @Override
    public Collection<Vacancy> findAll() {
        return vacancies.values();
    }
}