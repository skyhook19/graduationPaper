package com.sagaleev;

import com.sagaleev.domain.model.DiseaseStatistics;
import com.sagaleev.domain.model.Hospital;
import com.sagaleev.domain.model.Role;
import com.sagaleev.domain.repository.DiseaseStatisticsRepository;
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

    private final DiseaseStatisticsRepository diseaseStatisticsRepository;
    private final HospitalRepository hospitalRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public DatabaseSeeder(DiseaseStatisticsRepository diseaseStatisticsRepository, HospitalRepository hospitalRepository,
                          RoleRepository roleRepository) {
        this.diseaseStatisticsRepository = diseaseStatisticsRepository;
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
        hospital.setLogin("hosp1");
        hospital.setPassword(bCryptPasswordEncoder.encode("123"));
        hospital.setPasswordConfirm(bCryptPasswordEncoder.encode("123"));
        hospital.setEmail("lala@gmail.com");
        hospital.setName("Областная больница");
        hospital.setAddress("ул. Советская");
        hospital.setRoles(Arrays.asList(role));

        hospitalRepository.save(hospital);
    }

    private void initStatistics(){
        Hospital hospital = hospitalRepository.findOneByName("Областная больница");

        List<DiseaseStatistics> statistics = new ArrayList<>();
        statistics.add(new DiseaseStatistics(2014, Month.JANUARY, 610,116, 103, 5, 264,	590,	332,	144,	376,	16,	245,	223,	90,	16,	100, hospital));
        statistics.add(new DiseaseStatistics(2014, Month.FEBRUARY, 868, 128, 102, 7, 168,  542, 331, 158, 382, 18, 229, 178, 84, 16, 84, hospital));
        statistics.add(new DiseaseStatistics(2014, Month.MARCH, 1461, 118, 103, 3, 186, 555, 366, 151, 401, 18, 253, 197, 93, 17, 93, hospital));
        statistics.add(new DiseaseStatistics(2014, Month.APRIL, 864, 95, 88, 3, 194, 564, 369, 133, 396, 23, 258, 211, 98, 11, 96, hospital));
        statistics.add(new DiseaseStatistics(2014, Month.MAY, 645, 119, 113, 1, 192, 555, 359, 134, 384, 15, 248, 210, 95, 12, 101, hospital));
        statistics.add(new DiseaseStatistics(2014, Month.JUNE, 507, 94, 88, 0, 190, 511, 321, 129, 403, 14, 201, 169, 68, 9, 80, hospital));
        statistics.add(new DiseaseStatistics(2014, Month.JULY, 479, 69, 60, 0, 190, 536, 334, 146, 407, 17, 204, 170, 44, 12, 100, hospital));
        statistics.add(new DiseaseStatistics(2014, Month.AUGUST, 584, 85, 69, 7, 185, 500, 324, 152, 405, 18, 218, 196, 21, 17, 82, hospital));
        statistics.add(new DiseaseStatistics(2014, Month.SEPTEMBER, 613, 117, 102, 5, 188, 505, 200, 126, 411, 9, 227, 200, 95, 28, 94, hospital));
        statistics.add(new DiseaseStatistics(2014, Month.OCTOBER, 852, 108, 97, 5, 210, 539, 190, 159, 417, 24, 253, 214, 90, 30, 73, hospital));
        statistics.add(new DiseaseStatistics(2014, Month.NOVEMBER, 666, 98, 92, 4, 210, 547, 170, 135, 449, 16, 260, 215, 47, 30, 69, hospital));
        statistics.add(new DiseaseStatistics(2014, Month.DECEMBER, 837, 104, 89, 6, 211, 546, 169, 158, 506, 14, 300, 202, 49, 32, 68, hospital));

        diseaseStatisticsRepository.save(statistics);
    }
}
