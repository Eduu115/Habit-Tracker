-- =========================================
-- Insertar usuarios de prueba
-- =========================================
INSERT INTO usuarios (username, email, password, role, enabled) VALUES
('juanperez', 'juan@example.com', '{noop}123456', 'ROLE_ADMIN', 1),
('anagomez', 'ana@example.com', '{noop}123456', 'ROLE_CLIENTE', 1),
('carloss', 'carlos@example.com', '{noop}123456', 'ROLE_CLIENTE', 1);

-- =========================================
-- Insertar hábitos de prueba
-- =========================================
INSERT INTO habitos (user_id, nombre, descripcion, frecuencia) VALUES
(1, 'Beber agua', 'Tomar al menos 2 litros de agua al día', 'Diario'),
(1, 'Ejercicio', 'Hacer ejercicio 30 minutos', 'Diario'),
(2, 'Leer', 'Leer 20 páginas de un libro', 'Diario'),
(2, 'Meditación', 'Meditar 10 minutos', 'Diario'),
(3, 'Aprender Java', 'Resolver ejercicios de Java', 'Semanal');

-- =========================================
-- Insertar seguimiento de hábitos
-- =========================================
-- Usuario 1
INSERT INTO seguimiento (habit_id, fecha, completado) VALUES
(1, '2025-11-23', TRUE),
(1, '2025-11-24', TRUE),
(2, '2025-11-23', FALSE),
(2, '2025-11-24', TRUE);

-- Usuario 2
INSERT INTO seguimiento (habit_id, fecha, completado) VALUES
(3, '2025-11-23', TRUE),
(3, '2025-11-24', TRUE),
(4, '2025-11-23', TRUE),
(4, '2025-11-24', FALSE);

-- Usuario 3
INSERT INTO seguimiento (habit_id, fecha, completado) VALUES
(5, '2025-11-23', TRUE),
(5, '2025-11-24', TRUE);
