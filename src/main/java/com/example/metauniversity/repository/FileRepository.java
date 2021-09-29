package com.example.metauniversity.repository;

import com.example.metauniversity.domain.File.File;
import com.example.metauniversity.domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {


}
