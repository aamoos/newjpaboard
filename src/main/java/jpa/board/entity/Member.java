package jpa.board.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;            //번호
    private String username;    //작성자
    private String password;    //비밀번호
    private String phoneNo;     //핸드폰번호
    private int age;         //나이

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private Authority authority; //권한 [ROLE_USER, ROLE_ADMIN]

    @OneToMany(mappedBy = "member")
    private List<Board> boards = new ArrayList<>();

    @Builder
    public Member(String username, String phoneNo, int age, Authority authority){
        this.username = username;
        this.phoneNo = phoneNo;
        this.age = age;
        this.authority = authority;
    }
}
