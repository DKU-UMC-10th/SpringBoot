package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.request.MemberReqDTO;
import com.example.umc10th.domain.member.dto.response.MemberResDTO;
import com.example.umc10th.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    private static final String LOGIN_HTML = """
            <!DOCTYPE html>
            <html lang="ko">
            <head>
                <meta charset="UTF-8">
                <title>로그인</title>
                <style>
                    * { box-sizing: border-box; margin: 0; padding: 0; }
                    body {
                        font-family: 'Apple SD Gothic Neo', Arial, sans-serif;
                        background: #f0f2f5;
                        display: flex;
                        justify-content: center;
                        align-items: center;
                        min-height: 100vh;
                    }
                    .login-box {
                        background: white;
                        padding: 48px 40px;
                        border-radius: 12px;
                        box-shadow: 0 4px 20px rgba(0,0,0,0.1);
                        width: 400px;
                    }
                    h2 {
                        text-align: center;
                        margin-bottom: 32px;
                        color: #1a1a2e;
                        font-size: 24px;
                    }
                    .form-group { margin-bottom: 20px; }
                    label {
                        display: block;
                        margin-bottom: 6px;
                        color: #555;
                        font-size: 14px;
                        font-weight: 600;
                    }
                    input {
                        width: 100%;
                        padding: 12px 14px;
                        border: 1.5px solid #ddd;
                        border-radius: 6px;
                        font-size: 15px;
                        outline: none;
                    }
                    input:focus { border-color: #6c63ff; }
                    button {
                        width: 100%;
                        padding: 13px;
                        background: #6c63ff;
                        color: white;
                        border: none;
                        border-radius: 6px;
                        font-size: 16px;
                        font-weight: 600;
                        cursor: pointer;
                        margin-top: 8px;
                    }
                    button:hover { background: #574fd6; }
                    .error {
                        color: #e53935;
                        text-align: center;
                        font-size: 14px;
                        margin-bottom: 16px;
                        padding: 10px;
                        background: #fce4e4;
                        border-radius: 6px;
                    }
                    .hint {
                        text-align: center;
                        margin-top: 20px;
                        font-size: 13px;
                        color: #888;
                    }
                </style>
            </head>
            <body>
                <div class="login-box">
                    <h2>UMC 10기 로그인</h2>
                    {{ERROR_MESSAGE}}
                    <form action="/login" method="post">
                        <div class="form-group">
                            <label for="email">이메일</label>
                            <input type="email" id="email" name="email"
                                   placeholder="이메일을 입력하세요" required />
                        </div>
                        <div class="form-group">
                            <label for="password">비밀번호</label>
                            <input type="password" id="password" name="password"
                                   placeholder="비밀번호를 입력하세요" required />
                        </div>
                        <button type="submit">로그인</button>
                    </form>
                    <p class="hint">테스트 계정: test@test.com / test1234</p>
                </div>
            </body>
            </html>
            """;

    private static final String TOKEN_HTML = """
            <!DOCTYPE html>
            <html lang="ko">
            <head>
                <meta charset="UTF-8">
                <title>로그인 성공</title>
            </head>
            <body>
                <script>
                    const token = "{{TOKEN}}";
                    localStorage.setItem("authorized", JSON.stringify({
                        "JWT TOKEN": {
                            "name": "JWT TOKEN",
                            "schema": { "type": "http", "scheme": "bearer", "bearerFormat": "JWT" },
                            "value": token
                        }
                    }));
                    window.location.href = "/swagger-ui/index.html";
                </script>
            </body>
            </html>
            """;

    @GetMapping(value = "/login", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> loginPage(
            @RequestParam(required = false) String error
    ) {
        String errorMessage = (error != null)
                ? "<p class=\"error\">이메일 또는 비밀번호가 올바르지 않습니다.</p>"
                : "";

        String html = LOGIN_HTML.replace("{{ERROR_MESSAGE}}", errorMessage);

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(html);
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> loginProcess(
            @RequestParam String email,
            @RequestParam String password
    ) {
        try {
            MemberResDTO.Login result = memberService.login(new MemberReqDTO.Login(email, password));
            String html = TOKEN_HTML.replace("{{TOKEN}}", result.accessToken());
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_HTML)
                    .body(html);
        } catch (Exception e) {
            return ResponseEntity.status(302)
                    .header("Location", "/login?error")
                    .build();
        }
    }
}
