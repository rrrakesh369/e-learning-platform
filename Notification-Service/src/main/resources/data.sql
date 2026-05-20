INSERT INTO notification
(user_id,type,title,message,is_read,created_at,read_at)
VALUES
(
'user1',
'COURSE_ENROLLED',
'Spring Boot Course',
'You enrolled in Spring Boot',
true,
CURRENT_TIMESTAMP,
CURRENT_TIMESTAMP
),

(
'user1',
'ASSIGNMENT_DUE',
'Assignment Due',
'Assignment deadline available',
true,
CURRENT_TIMESTAMP,
CURRENT_TIMESTAMP
),

(
'user1',
'CERTIFICATE_ISSUED',
'Certificate Ready',
'Certificate issued successfully',
false,
CURRENT_TIMESTAMP,
NULL
),

(
'user1',
'SYSTEM_ALERT',
'Maintenance Alert',
'System maintenance scheduled',
false,
CURRENT_TIMESTAMP,
NULL
),

(
'user1',
'COURSE_ENROLLED',
'Java Course',
'Java enrolled successfully',
false,
CURRENT_TIMESTAMP,
NULL
),

(
'user2',
'ASSIGNMENT_DUE',
'Pending Assignment',
'Complete assignment',
false,
CURRENT_TIMESTAMP,
NULL
),

(
'user2',
'SYSTEM_ALERT',
'Password Reset',
'Update password',
true,
CURRENT_TIMESTAMP,
CURRENT_TIMESTAMP
);