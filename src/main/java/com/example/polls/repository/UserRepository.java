package com.example.polls.repository;

import com.example.polls.model.User;
import com.example.polls.payload.SolvedSubMasterClassRequest;
import com.example.polls.payload.UpdateProfileRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by nikola brodar on 20/9/2019
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameOrEmail(String username, String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE User s set  s.name = CASE WHEN :#{#userRequest.name} = '' THEN :#{#userRequest.oldName} ELSE :#{#userRequest.name} END," +
            " s.email = CASE WHEN :#{#userRequest.email} = '' THEN :#{#userRequest.oldEmail} ELSE :#{#userRequest.email} END " +
            " WHERE s.username = :#{#userRequest.oldUsername} and s.email = :#{#userRequest.oldEmail} ")
    int updateUserProfile(@Param("userRequest") UpdateProfileRequest userRequest);

    // STARI NACIN KAKO SAM SLAO VIŠE PARAMETRI OD STRINGOVI
    //@Query("UPDATE User s SET s.name = :name  WHERE s.username = :oldUsername")
    //int updateUserProfile(@Param("name") String name, @Param("oldUsername") String oldUsername);


    @Modifying
    @Transactional
   /* @Query(value = "INSERT INTO SubMasterClass s (s.name, s.id_user, s.id_masterclass, s.solved) " +
            "VALUES (:#{#solvedSubMasterClassRequest.name}, :#{#solvedSubMasterClassRequest.id_user}, " +
            ":#{#solvedSubMasterClassRequest.id_masterclass}, :#{#solvedSubMasterClassRequest.solved} )", nativeQuery = true)*/
    @Query(value = "INSERT INTO submasterclass  ( name, master_class_name, id_user, id_masterclass, solved, subMasterClassUniqueId, fileName) " +
            "VALUES (:#{#solvedSubMasterClassRequest.name}, :#{#solvedSubMasterClassRequest.masterClassName}, :#{#solvedSubMasterClassRequest.id_user}, " +
            ":#{#solvedSubMasterClassRequest.id_masterclass}, :#{#solvedSubMasterClassRequest.solved}," +
            ":#{#solvedSubMasterClassRequest.fileName}, :#{#solvedSubMasterClassRequest.subMasterClassUniqueId} )", nativeQuery = true)
    int solvedSubMasterClass(@Param("solvedSubMasterClassRequest") SolvedSubMasterClassRequest solvedSubMasterClassRequest);


}
