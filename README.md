# Menu API — Spring Boot REST

API REST de un menú de restaurante. Expone `GET` y `POST` sobre platos.

| Campo        | Tipo    | Regla               |
|--------------|---------|---------------------|
| `id`         | Long    | lo asigna el servidor |
| `nombre`     | String  | obligatorio         |
| `categoria`  | String  | obligatorio         |
| `precio`     | Double  | obligatorio, > 0    |
| `disponible` | Boolean | obligatorio         |

## Endpoints

| Método | URL                | Respuesta |
|--------|--------------------|-----------|
| GET    | `/api/platos`      | 200 + lista de platos |
| GET    | `/api/platos/{id}` | 200 + plato, o 404 |
| POST   | `/api/platos`      | 201 + plato creado, o 400 si es inválido |

Ejemplo de body para POST:

```json
{ "nombre": "Aji de gallina", "categoria": "Fondo", "precio": 25.0, "disponible": true }
```

## Ejecutar en GitHub Codespaces

1. En GitHub: **Code → Codespaces → Create codespace on main**.
2. Esperar a que termine la configuración (descarga dependencias automáticamente).
3. En la terminal: `./mvnw spring-boot:run`
4. Pestaña **Ports** → puerto 8080 → clic derecho → **Port Visibility → Public**.
5. Copiar la URL del puerto (`https://<nombre>-8080.app.github.dev`) y usarla en Postman/Insomnia,
   por ejemplo `GET https://<nombre>-8080.app.github.dev/api/platos`.

También se puede probar dentro del Codespace abriendo `peticiones.http` (extensión REST Client) y pulsando **Send Request**.

## Pruebas

```bash
./mvnw test
```
