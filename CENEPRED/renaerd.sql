SELECT * FROM public.evar
ORDER BY id_tramite ASC ;

SELECT  uni.nombre,count(*) total
FROM usuario u
JOIN profesional pl ON u.id_usuario = pl.id_usuario
JOIN profesion pn ON pl.id_profesion = pn.id_profesion
 JOIN universidad uni ON pl.id_universidad = uni.id_universidad GROUP BY 1 ORDER BY 2 DESC;


 SELECT u.nombre, u.ap, u.am, t.id_tramite, r.numero, r.fecha_vencimiento
FROM usuario u
JOIN tramite t ON u.id_usuario = t.id_usuario
JOIN evar e ON t.id_tramite = e.id_tramite
JOIN resolucion r ON e.id_resolucion = r.id_resolucion
WHERE r.fecha_vencimiento < NOW();

/***USUARIOS CON EVALUACION EN ALTO RIESGO***/
SELECT u.nombre, u.ap, u.am, e.identificacion_peligro, e.analisis_vulnerabilidad
FROM usuario u
JOIN tramite t ON u.id_usuario = t.id_usuario
JOIN evar e ON t.id_tramite = e.id_tramite
WHERE e.identificacion_peligro = 'n' AND e.analisis_vulnerabilidad = 's';