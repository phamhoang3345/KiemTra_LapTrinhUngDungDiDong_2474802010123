package vn.edu.vlu.kiemtra_laptrinhungdungdidong_2474802010123

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfileActivity : AppCompatActivity() {

    private lateinit var tvProfileTitle: TextView
    private lateinit var tvUserEmail: TextView
    private lateinit var btnLogout: Button
    private lateinit var lvSkills: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 2.2 & 3.2. Ánh xạ View bằng findViewById
        tvProfileTitle = findViewById(R.id.tvProfileTitle)
        tvUserEmail = findViewById(R.id.tvUserEmail)
        btnLogout = findViewById(R.id.btnLogout)
        lvSkills = findViewById(R.id.lvSkills)

        // 2.4. Trích xuất dữ liệu từ Intent / Bundle
        val email = intent.getStringExtra("USER_EMAIL")
            ?: intent.extras?.getString("USER_EMAIL")
            ?: ""

        tvUserEmail.text = getString(R.string.user_email_format, email)

        // 2.5. Xử lý Đăng xuất
        btnLogout.setOnClickListener {
            finish()
        }

        // 3.3. Khởi tạo mảng dữ liệu gồm 05 môn học/kỹ năng
        val skillList = listOf(
            "Lập trình Kotlin",
            "Thiết kế Layout XML",
            "Quản lý Git/GitHub",
            "Xử lý Intent",
            "Kiến trúc Android",
        )

        // 3.4. Gắn ArrayAdapter với android.R.layout.simple_list_item_1
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            skillList,
        )
        lvSkills.adapter = adapter

        // 3.5. Bắt sự kiện Click hiển thị Toast thông báo phần tử đã chọn
        lvSkills.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = skillList[position]
            Toast.makeText(this, selectedItem, Toast.LENGTH_SHORT).show()
        }
    }
}
