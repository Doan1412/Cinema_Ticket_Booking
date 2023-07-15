# Cinema_Ticket_Booking

Giới thiệu
Tên project : Cinema_Ticket_Booking
Chức năng chính: Đặt vé xem phim online
Công nghệ : Backend: Spring Boot (branch master)
            Frontend: Nuxtjs (branch client)

Bối cảnh thực hiện sản phẩm
Lý do thực hiện : Khi thực hiện project Quản lý rạp phim đơn giản bằng ngôn ngữ C ở trường tôi nhận thấy đề tài có nhiều hướng và tiềm năng phát triển hơn nữa đặc biệt ở môi trường website. Ngoài ra tôi cũng cảm thấy hứng thú trong việc thiết kế hệ thống với các đối tượng có các mối quan hệ phức tạp.
Thời gian thực hiện: Phần lớn được viết trong khoảng 11 ngày (4/7/2023 - 15/7/2023)
Vai trò của bản thân trong quá trình phát triển sản phẩm : Là project cá nhân

Các chức năng của sản phẩm
Các chức năng chung :
  -Login
  ![image](https://github.com/Doan1412/Cinema_Ticket_Booking/assets/85919961/6e43bd09-5b9e-498f-8384-cc167c5c5724)

  -Logout
Người dùng là khách hàng
  -Xem danh sách các phim đang chiếu
  ![image](https://github.com/Doan1412/Cinema_Ticket_Booking/assets/85919961/3349a3c0-c03f-40f6-b43f-66c385378ac4)

  -Xem thông tin phim cơ bản của phim như : trailer, poster, tên, diễn viên, thời lượng, v.v
  ![image](https://github.com/Doan1412/Cinema_Ticket_Booking/assets/85919961/857fa9cf-5931-4bcb-9503-04148aa59e7d)

  -Đặt vé: Cho phép đặt vé phim đã chọn. Có thể tự do chọn ngày, suất chiếu, ghế ngồi.
  ![image](https://github.com/Doan1412/Cinema_Ticket_Booking/assets/85919961/e36b244b-496a-452b-86c7-ee5b9e8e85d7)

  -Xem lịch sử đặt vé: Xem lại thông tin các vé đã được đặt.
  ![image](https://github.com/Doan1412/Cinema_Ticket_Booking/assets/85919961/b76cdbae-a6f8-47d4-a628-7ec025684e2e)

Người dùng là staff
  -Quản lý các phòng chiếu phim: Có thể xem, sửa, xóa thông tin các phòng chiếu phim bao gồm: số lượng ghế, số ghế theo chiều ngang/ dọc, loại ghế (regular, premier, couple,...)
  ![image](https://github.com/Doan1412/Cinema_Ticket_Booking/assets/85919961/9dc9db30-907c-42fe-88d7-06ef3d58cd1c)
  
  ![image](https://github.com/Doan1412/Cinema_Ticket_Booking/assets/85919961/1dfa6d8e-7e5d-4da6-a514-3810d713f100)
  
  -Quản lý phim: Xem, sửa, xóa các thông tin của phim
  ![image](https://github.com/Doan1412/Cinema_Ticket_Booking/assets/85919961/20f931ef-669e-4afb-b504-753b734c6c4c)

  ![image](https://github.com/Doan1412/Cinema_Ticket_Booking/assets/85919961/ca701f36-7e41-4456-842d-a18bee77ee64)

  -Quản lý suất chiếu phim: Xem, sửa, xóa các thông tin của suất chiếu bao gồm thời gian bắt đầu chiếu, thời lượng, giá vé theo từng loại ghế, v.v
  ![image](https://github.com/Doan1412/Cinema_Ticket_Booking/assets/85919961/f1974e55-8006-49fd-8a9b-05aa8f661aa3)

  ![image](https://github.com/Doan1412/Cinema_Ticket_Booking/assets/85919961/743801ec-e63c-40ba-bd3b-c9c32ee332f3)
  
Video demo các chức năng : https://youtu.be/a3YZ2G7QPdk

Những điểm nhấn về mặt kỹ thuật:
  -Bảo mật: Sử dụng JSON Web Token (JWT) và refresh token để đăng nhập, phân quyền và thiết lập các lớp bảo mật ở server bắng Spring Security 
  ![image](https://github.com/Doan1412/Cinema_Ticket_Booking/assets/85919961/bcede6d3-5eed-4076-9100-2433c56fe25a)
