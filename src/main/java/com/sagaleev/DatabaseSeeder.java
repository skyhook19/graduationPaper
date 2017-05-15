package com.sagaleev;

import com.sagaleev.domain.model.AmbulanceCallStats;
import com.sagaleev.domain.model.Disease;
import com.sagaleev.domain.model.Hospital;
import com.sagaleev.domain.model.Role;
import com.sagaleev.domain.repository.AmbulanceCallStatsRepository;
import com.sagaleev.domain.repository.HospitalRepository;
import com.sagaleev.domain.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseSeeder {

    private final AmbulanceCallStatsRepository ambulanceCallStatsRepository;
    private final HospitalRepository hospitalRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public DatabaseSeeder(AmbulanceCallStatsRepository ambulanceCallStatsRepository, HospitalRepository hospitalRepository,
                          RoleRepository roleRepository) {
        this.ambulanceCallStatsRepository = ambulanceCallStatsRepository;
        this.hospitalRepository = hospitalRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        initRoles();
        initHospitals();
        initStatistics();
    }

    private void initRoles(){
        Role role1 = new Role();
        role1.setAuthority("HospitalAccount");
        roleRepository.save(role1);
    }

    private void initHospitals(){
        Role role = roleRepository.findByAuthority("HospitalAccount");

        Hospital hospital = new Hospital();
        hospital.setLogin("hospital1");
        hospital.setPassword(bCryptPasswordEncoder.encode("toku19"));
        hospital.setPasswordConfirm(bCryptPasswordEncoder.encode("toku19"));
        hospital.setEmail("lala@gmail.com");
        hospital.setName("Саратовский ГМУ им. В. И. Разумовского Минздрава России");
        hospital.setAddress("ул. Большая Садовая");
        hospital.setRoles(Arrays.asList(role));

        hospitalRepository.save(hospital);
    }

    private void initStatistics(){
        Hospital hospital = hospitalRepository.findByLogin("hospital1");

        List<AmbulanceCallStats> statistics = new ArrayList<>();

        statistics.add(new AmbulanceCallStats(2014, Month.JANUARY, Disease.ACUTE_RESPIRATORY_VIRAL_INFECTION, 610, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JANUARY, Disease.MYOCARDIAL_INFARCTION, 116, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JANUARY, Disease.MYOCARDIAL_INFARCTION_WITH_HOSPITALIZATION, 103, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JANUARY, Disease.FATAL_MYOCARDIAL_INFARCTION, 5, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JANUARY, Disease.ANGINA_PECTORIS, 264, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JANUARY, Disease.ARRHYTHMIA, 590, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JANUARY, Disease.CARDIO_VASCULAR_DISEASE, 332, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JANUARY, Disease.FATAL_CARDIO_VASCULAR_DISEASE, 144, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JANUARY, Disease.STROKE, 376, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JANUARY, Disease.FATAL_STROKE, 16, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JANUARY, Disease.PNEUMONIA, 245, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JANUARY, Disease.BRONCHIAL_ASTHMA, 223, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JANUARY, Disease.BRONCHITIS, 90, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JANUARY, Disease.PEPTIC_ULCER_DISEASE, 16, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JANUARY, Disease.GASTRITIS, 100, hospital));


        statistics.add(new AmbulanceCallStats(2014, Month.FEBRUARY, Disease.ACUTE_RESPIRATORY_VIRAL_INFECTION, 868, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.FEBRUARY, Disease.MYOCARDIAL_INFARCTION, 128, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.FEBRUARY, Disease.MYOCARDIAL_INFARCTION_WITH_HOSPITALIZATION, 102, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.FEBRUARY, Disease.FATAL_MYOCARDIAL_INFARCTION, 7, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.FEBRUARY, Disease.ANGINA_PECTORIS, 168, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.FEBRUARY, Disease.ARRHYTHMIA, 542, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.FEBRUARY, Disease.CARDIO_VASCULAR_DISEASE, 331, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.FEBRUARY, Disease.FATAL_CARDIO_VASCULAR_DISEASE, 158, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.FEBRUARY, Disease.STROKE, 382, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.FEBRUARY, Disease.FATAL_STROKE, 18, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.FEBRUARY, Disease.PNEUMONIA, 229, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.FEBRUARY, Disease.BRONCHIAL_ASTHMA, 178, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.FEBRUARY, Disease.BRONCHITIS, 84, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.FEBRUARY, Disease.PEPTIC_ULCER_DISEASE, 16, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.FEBRUARY, Disease.GASTRITIS, 84, hospital));

        statistics.add(new AmbulanceCallStats(2014, Month.MARCH, Disease.ACUTE_RESPIRATORY_VIRAL_INFECTION, 1461, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.MARCH, Disease.MYOCARDIAL_INFARCTION, 118, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.MARCH, Disease.MYOCARDIAL_INFARCTION_WITH_HOSPITALIZATION, 103, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.MARCH, Disease.FATAL_MYOCARDIAL_INFARCTION, 3, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.MARCH, Disease.ANGINA_PECTORIS, 186, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.MARCH, Disease.ARRHYTHMIA, 555, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.MARCH, Disease.CARDIO_VASCULAR_DISEASE, 366, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.MARCH, Disease.FATAL_CARDIO_VASCULAR_DISEASE, 151, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.MARCH, Disease.STROKE, 401, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.MARCH, Disease.FATAL_STROKE, 18, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.MARCH, Disease.PNEUMONIA, 253, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.MARCH, Disease.BRONCHIAL_ASTHMA, 197, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.MARCH, Disease.BRONCHITIS, 93, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.MARCH, Disease.PEPTIC_ULCER_DISEASE, 17, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.MARCH, Disease.GASTRITIS, 93, hospital));

        statistics.add(new AmbulanceCallStats(2014, Month.APRIL, Disease.ACUTE_RESPIRATORY_VIRAL_INFECTION, 864, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.APRIL, Disease.MYOCARDIAL_INFARCTION, 95, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.APRIL, Disease.MYOCARDIAL_INFARCTION_WITH_HOSPITALIZATION, 88, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.APRIL, Disease.FATAL_MYOCARDIAL_INFARCTION, 3, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.APRIL, Disease.ANGINA_PECTORIS, 194, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.APRIL, Disease.ARRHYTHMIA, 564, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.APRIL, Disease.CARDIO_VASCULAR_DISEASE, 369, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.APRIL, Disease.FATAL_CARDIO_VASCULAR_DISEASE, 133, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.APRIL, Disease.STROKE, 396, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.APRIL, Disease.FATAL_STROKE, 23, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.APRIL, Disease.PNEUMONIA, 258, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.APRIL, Disease.BRONCHIAL_ASTHMA, 211, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.APRIL, Disease.BRONCHITIS, 98, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.APRIL, Disease.PEPTIC_ULCER_DISEASE, 11, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.APRIL, Disease.GASTRITIS, 96, hospital));

        statistics.add(new AmbulanceCallStats(2014, Month.MAY, Disease.ACUTE_RESPIRATORY_VIRAL_INFECTION, 645, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.MAY, Disease.MYOCARDIAL_INFARCTION, 119, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.MAY, Disease.MYOCARDIAL_INFARCTION_WITH_HOSPITALIZATION, 113, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.MAY, Disease.FATAL_MYOCARDIAL_INFARCTION, 1, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.MAY, Disease.ANGINA_PECTORIS, 192, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.MAY, Disease.ARRHYTHMIA, 555, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.MAY, Disease.CARDIO_VASCULAR_DISEASE, 359, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.MAY, Disease.FATAL_CARDIO_VASCULAR_DISEASE, 134, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.MAY, Disease.STROKE, 384, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.MAY, Disease.FATAL_STROKE, 15, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.MAY, Disease.PNEUMONIA, 248, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.MAY, Disease.BRONCHIAL_ASTHMA, 210, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.MAY, Disease.BRONCHITIS, 95, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.MAY, Disease.PEPTIC_ULCER_DISEASE, 12, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.MAY, Disease.GASTRITIS, 101, hospital));

        statistics.add(new AmbulanceCallStats(2014, Month.JUNE, Disease.ACUTE_RESPIRATORY_VIRAL_INFECTION, 507, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JUNE, Disease.MYOCARDIAL_INFARCTION, 94, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JUNE, Disease.MYOCARDIAL_INFARCTION_WITH_HOSPITALIZATION, 88, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JUNE, Disease.FATAL_MYOCARDIAL_INFARCTION, 0, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JUNE, Disease.ANGINA_PECTORIS, 190, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JUNE, Disease.ARRHYTHMIA, 511, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JUNE, Disease.CARDIO_VASCULAR_DISEASE, 321, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JUNE, Disease.FATAL_CARDIO_VASCULAR_DISEASE, 129, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JUNE, Disease.STROKE, 403, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JUNE, Disease.FATAL_STROKE, 14, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JUNE, Disease.PNEUMONIA, 201, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JUNE, Disease.BRONCHIAL_ASTHMA, 169, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JUNE, Disease.BRONCHITIS, 68, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JUNE, Disease.PEPTIC_ULCER_DISEASE, 9, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JUNE, Disease.GASTRITIS, 80, hospital));

        statistics.add(new AmbulanceCallStats(2014, Month.JULY, Disease.ACUTE_RESPIRATORY_VIRAL_INFECTION, 479, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JULY, Disease.MYOCARDIAL_INFARCTION, 69, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JULY, Disease.MYOCARDIAL_INFARCTION_WITH_HOSPITALIZATION, 60, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JULY, Disease.FATAL_MYOCARDIAL_INFARCTION, 0, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JULY, Disease.ANGINA_PECTORIS, 190, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JULY, Disease.ARRHYTHMIA, 536, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JULY, Disease.CARDIO_VASCULAR_DISEASE, 334, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JULY, Disease.FATAL_CARDIO_VASCULAR_DISEASE, 146, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JULY, Disease.STROKE, 407, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JULY, Disease.FATAL_STROKE, 17, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JULY, Disease.PNEUMONIA, 204, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JULY, Disease.BRONCHIAL_ASTHMA, 170, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JULY, Disease.BRONCHITIS, 44, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JULY, Disease.PEPTIC_ULCER_DISEASE, 12, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.JULY, Disease.GASTRITIS, 100, hospital));

        statistics.add(new AmbulanceCallStats(2014, Month.AUGUST, Disease.ACUTE_RESPIRATORY_VIRAL_INFECTION, 584, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.AUGUST, Disease.MYOCARDIAL_INFARCTION, 85, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.AUGUST, Disease.MYOCARDIAL_INFARCTION_WITH_HOSPITALIZATION, 69, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.AUGUST, Disease.FATAL_MYOCARDIAL_INFARCTION, 7, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.AUGUST, Disease.ANGINA_PECTORIS, 185, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.AUGUST, Disease.ARRHYTHMIA, 500, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.AUGUST, Disease.CARDIO_VASCULAR_DISEASE, 324, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.AUGUST, Disease.FATAL_CARDIO_VASCULAR_DISEASE, 152, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.AUGUST, Disease.STROKE, 405, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.AUGUST, Disease.FATAL_STROKE, 18, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.AUGUST, Disease.PNEUMONIA, 218, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.AUGUST, Disease.BRONCHIAL_ASTHMA, 196, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.AUGUST, Disease.BRONCHITIS, 21, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.AUGUST, Disease.PEPTIC_ULCER_DISEASE, 17, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.AUGUST, Disease.GASTRITIS, 82, hospital));

        statistics.add(new AmbulanceCallStats(2014, Month.AUGUST, Disease.ACUTE_RESPIRATORY_VIRAL_INFECTION, 584, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.AUGUST, Disease.MYOCARDIAL_INFARCTION, 85, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.AUGUST, Disease.MYOCARDIAL_INFARCTION_WITH_HOSPITALIZATION, 69, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.AUGUST, Disease.FATAL_MYOCARDIAL_INFARCTION, 7, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.AUGUST, Disease.ANGINA_PECTORIS, 185, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.AUGUST, Disease.ARRHYTHMIA, 500, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.AUGUST, Disease.CARDIO_VASCULAR_DISEASE, 324, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.AUGUST, Disease.FATAL_CARDIO_VASCULAR_DISEASE, 152, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.AUGUST, Disease.STROKE, 405, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.AUGUST, Disease.FATAL_STROKE, 18, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.AUGUST, Disease.PNEUMONIA, 218, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.AUGUST, Disease.BRONCHIAL_ASTHMA, 196, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.AUGUST, Disease.BRONCHITIS, 21, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.AUGUST, Disease.PEPTIC_ULCER_DISEASE, 17, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.AUGUST, Disease.GASTRITIS, 82, hospital));

        statistics.add(new AmbulanceCallStats(2014, Month.SEPTEMBER, Disease.ACUTE_RESPIRATORY_VIRAL_INFECTION, 613, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.SEPTEMBER, Disease.MYOCARDIAL_INFARCTION, 117, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.SEPTEMBER, Disease.MYOCARDIAL_INFARCTION_WITH_HOSPITALIZATION, 102, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.SEPTEMBER, Disease.FATAL_MYOCARDIAL_INFARCTION, 5, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.SEPTEMBER, Disease.ANGINA_PECTORIS, 188, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.SEPTEMBER, Disease.ARRHYTHMIA, 505, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.SEPTEMBER, Disease.CARDIO_VASCULAR_DISEASE, 200, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.SEPTEMBER, Disease.FATAL_CARDIO_VASCULAR_DISEASE, 126, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.SEPTEMBER, Disease.STROKE, 411, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.SEPTEMBER, Disease.FATAL_STROKE, 9, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.SEPTEMBER, Disease.PNEUMONIA, 227, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.SEPTEMBER, Disease.BRONCHIAL_ASTHMA, 200, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.SEPTEMBER, Disease.BRONCHITIS, 95, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.SEPTEMBER, Disease.PEPTIC_ULCER_DISEASE, 28, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.SEPTEMBER, Disease.GASTRITIS, 94, hospital));

        statistics.add(new AmbulanceCallStats(2014, Month.OCTOBER, Disease.ACUTE_RESPIRATORY_VIRAL_INFECTION, 852, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.OCTOBER, Disease.MYOCARDIAL_INFARCTION, 108, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.OCTOBER, Disease.MYOCARDIAL_INFARCTION_WITH_HOSPITALIZATION, 97, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.OCTOBER, Disease.FATAL_MYOCARDIAL_INFARCTION, 5, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.OCTOBER, Disease.ANGINA_PECTORIS, 210, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.OCTOBER, Disease.ARRHYTHMIA, 539, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.OCTOBER, Disease.CARDIO_VASCULAR_DISEASE, 190, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.OCTOBER, Disease.FATAL_CARDIO_VASCULAR_DISEASE, 159, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.OCTOBER, Disease.STROKE, 417, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.OCTOBER, Disease.FATAL_STROKE, 24, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.OCTOBER, Disease.PNEUMONIA, 253, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.OCTOBER, Disease.BRONCHIAL_ASTHMA, 214, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.OCTOBER, Disease.BRONCHITIS, 90, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.OCTOBER, Disease.PEPTIC_ULCER_DISEASE, 30, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.OCTOBER, Disease.GASTRITIS, 73, hospital));

        statistics.add(new AmbulanceCallStats(2014, Month.NOVEMBER, Disease.ACUTE_RESPIRATORY_VIRAL_INFECTION, 666, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.NOVEMBER, Disease.MYOCARDIAL_INFARCTION, 98, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.NOVEMBER, Disease.MYOCARDIAL_INFARCTION_WITH_HOSPITALIZATION, 92, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.NOVEMBER, Disease.FATAL_MYOCARDIAL_INFARCTION, 4, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.NOVEMBER, Disease.ANGINA_PECTORIS, 210, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.NOVEMBER, Disease.ARRHYTHMIA, 547, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.NOVEMBER, Disease.CARDIO_VASCULAR_DISEASE, 170, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.NOVEMBER, Disease.FATAL_CARDIO_VASCULAR_DISEASE, 135, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.NOVEMBER, Disease.STROKE, 449, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.NOVEMBER, Disease.FATAL_STROKE, 16, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.NOVEMBER, Disease.PNEUMONIA, 260, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.NOVEMBER, Disease.BRONCHIAL_ASTHMA, 215, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.NOVEMBER, Disease.BRONCHITIS, 47, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.NOVEMBER, Disease.PEPTIC_ULCER_DISEASE, 30, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.NOVEMBER, Disease.GASTRITIS, 69, hospital));

        statistics.add(new AmbulanceCallStats(2014, Month.DECEMBER, Disease.ACUTE_RESPIRATORY_VIRAL_INFECTION, 837, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.DECEMBER, Disease.MYOCARDIAL_INFARCTION, 104, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.DECEMBER, Disease.MYOCARDIAL_INFARCTION_WITH_HOSPITALIZATION, 89, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.DECEMBER, Disease.FATAL_MYOCARDIAL_INFARCTION, 6, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.DECEMBER, Disease.ANGINA_PECTORIS, 211, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.DECEMBER, Disease.ARRHYTHMIA, 546, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.DECEMBER, Disease.CARDIO_VASCULAR_DISEASE, 169, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.DECEMBER, Disease.FATAL_CARDIO_VASCULAR_DISEASE, 158, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.DECEMBER, Disease.STROKE, 506, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.DECEMBER, Disease.FATAL_STROKE, 14, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.DECEMBER, Disease.PNEUMONIA, 300, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.DECEMBER, Disease.BRONCHIAL_ASTHMA, 202, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.DECEMBER, Disease.BRONCHITIS, 49, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.DECEMBER, Disease.PEPTIC_ULCER_DISEASE, 32, hospital));
        statistics.add(new AmbulanceCallStats(2014, Month.DECEMBER, Disease.GASTRITIS, 68, hospital));

        ambulanceCallStatsRepository.save(statistics);
    }
}
