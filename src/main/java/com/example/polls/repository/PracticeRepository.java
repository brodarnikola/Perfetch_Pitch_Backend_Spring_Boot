package com.example.polls.repository;

import com.example.polls.model.MasterClass;
import com.example.polls.model.SubMasterClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PracticeRepository  extends JpaRepository<SubMasterClass, Long> {

    //List<SubMasterClass> findById_userIn(Long Id_user);

    @Query("SELECT s FROM SubMasterClass s where s.id_user = :userId")
    List<SubMasterClass> getSubMasterClassAndMasterClass(@Param("userId") Long userId);

    @Query("SELECT m FROM MasterClass m where m.id_user = :userId")
    List<MasterClass> getMasterClassAndMasterClass(@Param("userId") Long userId);
}
