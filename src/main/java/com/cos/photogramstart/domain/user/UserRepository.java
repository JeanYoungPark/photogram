package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository 상속시 어노테이션 없어도 IoC에 자동으로 등록된다.
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
