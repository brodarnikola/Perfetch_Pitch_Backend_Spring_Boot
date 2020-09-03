package com.example.polls.service;

import com.example.polls.model.*;
import com.example.polls.repository.PracticeRepository;
import com.example.polls.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class PracticeService {

    @Autowired
    private PracticeRepository practiceRepository;

    public List<SubMasterClass> getUserSubMasterClassData(final UserPrincipal user) {

        List<SubMasterClass> subMasterClassList = practiceRepository.getSubMasterClassAndMasterClass(user.getId());
        return subMasterClassList;
    }

    public List<MasterClass> getUserMasterClassData(final UserPrincipal user) {

        List<MasterClass> masterClassList = practiceRepository.getMasterClassAndMasterClass(user.getId());
        return masterClassList;
    }


    public List<SubMasterClass> getUserSubMasterClassDataSplashScreen(final Long userId) {

        List<SubMasterClass> subMasterClassList = practiceRepository.getSubMasterClassAndMasterClass(userId);
        return subMasterClassList;
    }

    public List<MasterClass> getUserMasterClassDataSplashScreen(final Long userId) {

        List<MasterClass> masterClassList = practiceRepository.getMasterClassAndMasterClass(userId);
        return masterClassList;
    }

}
