insert into user (id, created_date_time, updated_date_time, email, is_email_verified, password, phone_number, invite_code, is_menstrual_cycle, location, nickname, profile_image_url, color, provider_type, role_type, user_unique_id, username, pair_user_id)
values  (1, '2024-06-30 16:41:22.570974', '2024-06-30 16:41:22.570974', 'dltjdgur327@gmail.com', true, 'NO_PASS', null, '투명한그늘#1922', false, null, null, 'https://lh3.googleusercontent.com/a/ACg8ocK-Q30tp_ikZ3E15cV5q-cxlXKQRt17vw4dg1q_3ntdCusOWw=s96-c', '#f0f8ff', 'GOOGLE', 'USER', '110804935771843356094', '이성혁', null),
    (2, '2024-06-30 16:41:36.172817', '2024-06-30 16:41:36.172817', 'dltjdgur327@naver.com', true, 'NO_PASS', null, '산뜻한겨울#0017', false, null, null, 'https://ssl.pstatic.net/static/pwe/address/img_profile.png', '#f0f8ff', 'NAVER', 'USER', '43779677', '이성혁', null),
    (3, '2024-06-30 16:41:58.247058', '2024-06-30 16:41:58.247058', 'dltjdgur327@nate.com', true, 'NO_PASS', null, '화려한초승달#0926', false, null, null, 'http://k.kakaocdn.net/dn/chuwXX/btqQuUPSIDp/SQSBj3jXxUSaMm1wWbbT40/img_110x110.jpg', '#f0f8ff', 'KAKAO', 'USER', '3328019351', '이성혁', null);

insert into user_notification_setting (id, created_date_time, updated_date_time, is_enabled, notification_type, user_id)
values  (1, '2024-07-01 11:08:40.114314', '2024-07-01 11:08:40.114314', false, 'INFORMATION', 1),
    (2, '2024-07-01 11:08:40.119917', '2024-07-01 11:08:40.119917', false, 'TODO_REMINDER', 1),
    (3, '2024-07-01 11:08:40.121405', '2024-07-01 11:08:40.121405', false, 'TODO', 1),
    (4, '2024-07-01 11:08:40.122447', '2024-07-01 11:08:40.122447', false, 'PAIR_NEW_TODO', 1),
    (5, '2024-07-01 11:08:40.123364', '2024-07-01 11:08:40.123364', false, 'PAIR_COMPLETE_TODO', 1),
    (6, '2024-07-01 11:08:40.124303', '2024-07-01 11:08:40.124303', false, 'PAIR_EMOJI', 1),
    (7, '2024-07-01 11:08:40.125515', '2024-07-01 11:08:40.125515', false, 'PAIR_DIARY', 1),
    (8, '2024-07-01 11:08:40.126477', '2024-07-01 11:08:40.126477', false, 'MENSTRUAL', 1);