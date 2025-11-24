CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) check (role in ('ROLE_ADMIN', 'ROLE_CLIENTE')) DEFAULT 'ROLE_CLIENTE' NOT NULL,
    enabled INT(2) CHECK (enabled in (0, 1)) DEFAULT 1 NOT NULL,  
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE habitos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    frecuencia VARCHAR(20) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES usuarios(id)
);

CREATE TABLE seguimiento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    habit_id INT NOT NULL,
    fecha DATE NOT NULL,
    completado BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (habit_id) REFERENCES habitos(id)
);
