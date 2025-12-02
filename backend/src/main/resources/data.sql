INSERT INTO roles (name, description) VALUES
('ROLE_ADMIN', 'Administrador general del sistema'),
('ROLE_CLIENTE', 'Usuario estándar que gestiona sus hábitos');

INSERT INTO usuarios (username, email, password, name, last_name, role_id, enabled)
VALUES
-- IMPORTANTE: Todos los usuarios tienen la contraseña: password123
-- Hash BCrypt válido para "password123" (todos usan el mismo hash para simplificar)
('admin1', 'admin1@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Carlos', 'Martínez', 1, 1),
('admin2', 'admin2@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Lucía', 'Gómez', 1, 1),

('user1', 'user1@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Pedro', 'López', 2, 1),
('user2', 'user2@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'María', 'Hernández', 2, 1),
('user3', 'user3@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Ana', 'Pérez', 2, 1),
('user4', 'user4@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Diego', 'Ruiz', 2, 1),
('user5', 'user5@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Sara', 'Iglesias', 2, 1),
('user6', 'user6@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'David', 'Ortega', 2, 1),
('user7', 'user7@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Laura', 'Santos', 2, 1),
('user8', 'user8@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Hugo', 'Navas', 2, 1);

INSERT INTO habitos (user_id, nombre, descripcion, frecuencia)
VALUES
-- User 1
(3, 'Leer 30 minutos', 'Lectura diaria para mejorar conocimientos', 'diario'),
(3, 'Hacer ejercicio', 'Rutina de 20 minutos', 'diario'),
(3, 'Beber 2L de agua', 'Control de hidratación', 'diario'),

-- User 2
(4, 'Salir a caminar', 'Caminata de 45 minutos', 'diario'),
(4, 'No usar el móvil antes de dormir', 'Higiene del sueño', 'diario'),

-- User 3
(5, 'Estudiar programación', 'Aprender nuevas tecnologías', 'diario'),
(5, 'Practicar inglés', 'Duolingo o vídeos', 'diario'),

-- User 4
(6, 'Meditar', 'Meditación guiada', 'diario'),

-- User 5
(7, 'Dibujar', 'Desarrollo artístico', 'semanal'),
(7, 'Tocar guitarra', 'Práctica musical', 'semanal'),

-- User 6
(8, 'Salir a correr', 'Running 5km', 'diario'),

-- User 7
(9, 'Cocinar saludable', 'Plan de comidas semanales', 'semanal'),

-- User 8
(10, 'Hacer estiramientos', '5 minutos cada mañana', 'diario');

INSERT INTO seguimiento (habit_id, fecha, completado)
VALUES
-- Habitos de user 1
(1, '2025-01-01', TRUE), (1, '2025-01-02', TRUE), (1, '2025-01-03', FALSE),
(2, '2025-01-01', TRUE), (2, '2025-01-02', FALSE), (2, '2025-01-03', TRUE),
(3, '2025-01-01', TRUE), (3, '2025-01-02', TRUE),

-- User 2
(4, '2025-01-01', TRUE), (4, '2025-01-02', TRUE),
(5, '2025-01-01', FALSE), (5, '2025-01-02', TRUE),

-- User 3
(6, '2025-01-01', TRUE), (6, '2025-01-02', TRUE),
(7, '2025-01-01', TRUE),

-- User 4
(8, '2025-01-01', TRUE), (8, '2025-01-02', FALSE),

-- User 5
(9, '2025-01-01', TRUE),
(10, '2025-01-01', TRUE),

-- User 6
(11, '2025-01-01', TRUE),(11, '2025-01-02', TRUE),

-- User 7
(12, '2025-01-01', TRUE),

-- User 8
(13, '2025-01-01', TRUE),(13, '2025-01-02', TRUE);