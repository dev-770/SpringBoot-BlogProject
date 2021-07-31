## SpringBoot-BlogProject
### 0. 프로젝트
#### 개발환경
- Language : Java, JavaScript, JQuery, JSP
- Tools : STS3, MysqlWorkbench 8.0 
- DB : MySQL 8.0.23
- WAS : Tomcat 9.0
- Framework : Spring-boot, Bootstrap
- Library : jstl, JPA, Lombok, Spring Security, OAuth(외부), SummerNote

***

### 1. 블로그

#### 메인

- PageableDefault로 이용한 페이징 처리
```
@GetMapping({"","/"})
public String indexMain(Model model, @PageableDefault(size=3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
  model.addAttribute("boards", boardService.list(pageable));
  return "index";
}
```

- 메인 페이지외 로그인 화면

#### 회원가입

- ajax 사용으로 회원가입 기능

- JPA 회원 개체 테이블 생성
```
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  ...
}
```
- DB에 저장 될 비밀번호를 해시 암호화 처리
```
/* Password 해쉬 암호화 처리 */
@Autowired
private BCryptPasswordEncoder encoder;
public void join function() {
String rawPassword = user.getPassword();
String encPassword= encoder.encode(rawPassword);
...
}
```

#### 로그인

- Spring Security로 이용한 로그인 처리 (시큐리티 세션 저장)
```
...
/* 시큐리티 세션 저장 */
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal" />
</sec:authorize>
...
```
- DB 비밀번호와 같게 하기 위해 세션 비밀번호 해시 처리 후 비교
```
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
  auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
}
```
- OAuth 카카오로 로그인 처리 할 수 있도록 토큰 처리 및 세션 처리
```
ObjectMapper objectMapper = new ObjectMapper();
OAuthToken oAuthToken = null;
try {
  oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
} catch (JsonMappingException e) {
  e.printStackTrace();
} catch (JsonProcessingException e) {
  e.printStackTrace();
}
```

#### 회원정보 수정
- ajax 사용으로 회원정보 수정 기능
- OAuth로 이용한 로그인은 수정X, 회원가입 수정O 처리
```
<c:choose>
    <c:when test="${empty principal.user.oauth }">
      ...
    </c:when>
</c:choose>
```

#### 게시판, 답글
- ajax 사용으로 CRUD 게시판 구현
```
... 
// ajax 전송
$.ajax({
  type: "POST",
  url: "/api/board",
  data: JSON.stringify(...),
  contentType: "application/json; charset=utf-8",
  dataType: "json"
}).done(function(resp){
  ...
}).fail(function(error){
  ...
}
...
```
- JPA 게시판, 답글 개체 테이블 생성

- 글쓰기 폼 SummerNote 사용

#### 프로젝트 문제 해결
- DB 중복회원 가입 문제 해결
```
// ajax
...
if(resp.status === 500){
...
} else { 
...
}
...
```
- 답글 foreign key 문제 해결
```
@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) // casecade 추가
```
