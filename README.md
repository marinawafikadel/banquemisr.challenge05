add the database credentials and the mail in the application.properies 
Database name : Task
CREATE TABLE tasks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    status ENUM('todo', 'in_progress', 'done') NOT NULL,
    priority ENUM('low', 'medium', 'high') NOT NULL,
    due_date DATE,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'USER', 'MANAGER') NOT NULL, -- Adjust role values based on your `Role` enum
    email VARCHAR(255)
);
CREATE TABLE history (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    change_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    task_id BIGINT NOT NULL,
    description VARCHAR(255),
    CONSTRAINT fk_task_history FOREIGN KEY (task_id) REFERENCES tasks (id) ON DELETE CASCADE
);
CREATE TABLE notifications (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    message VARCHAR(255),
    is_read BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user_notification FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);


