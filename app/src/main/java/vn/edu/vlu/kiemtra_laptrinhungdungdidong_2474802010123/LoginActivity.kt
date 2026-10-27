package vn.edu.vlu.kiemtra_laptrinhungdungdidong_2474802010123

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

open class LoginActivity : AppCompatActivity() {

    private lateinit var tvTitle: TextView
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1.3. Ánh xạ View bằng findViewById truyền thống
        tvTitle = findViewById(R.id.tvTitle)
        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvStatus = findViewById(R.id.tvStatus)

        // 1.4. Xử lý sự kiện click vào Button 'Đăng nhập'
        btnLogin.setOnClickListener {
            handleLogin()
        }
    }

    private fun handleLogin() {
        val email = edtEmail.text.toString().trim()
        val password = edtPassword.text.toString().trim()

        if (email.isEmpty() && password.isEmpty()) {
            tvStatus.text = "Vui lòng nhập Email và Mật khẩu!"
            tvStatus.setTextColor(Color.RED)
        } else if (email.isEmpty()) {
            tvStatus.text = "Vui lòng nhập Email!"
            tvStatus.setTextColor(Color.RED)
        } else if (password.isEmpty()) {
            tvStatus.text = "Vui lòng nhập Mật khẩu!"
            tvStatus.setTextColor(Color.RED)
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tvStatus.text = "Email sai định dạng!"
            tvStatus.setTextColor(Color.RED)
        } else if (password.length < 6) {
            tvStatus.text = "Mật khẩu phải từ 6 ký tự trở lên!"
            tvStatus.setTextColor(Color.RED)
        } else {
            tvStatus.text = getString(R.string.auth_success)
            tvStatus.setTextColor(Color.parseColor("#2E7D32"))

            // 2.3 & 2.4. Tạo Explicit Intent và truyền dữ liệu Bundle / Extra
            val intent = Intent(this, ProfileActivity::class.java).apply {
                putExtra("USER_EMAIL", email)
            }
            startActivity(intent)
        }
    }
}
