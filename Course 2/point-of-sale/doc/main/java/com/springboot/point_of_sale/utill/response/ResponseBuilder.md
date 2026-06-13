# `ResponseBuilder` Class Documentation

**Package:** `com.springboot.point_of_sale.util.response`
**Purpose:** Centralizes the creation of API responses in a **consistent, reusable, and type-safe manner** for the entire Spring Boot application.

This builder ensures:

1. Consistent response structure (`StandardResponse<T>`).
2. Correct HTTP status codes for success and error responses.
3. Reduced boilerplate in controllers.
4. Stronger API contracts and readability.

---

## 1. Class Declaration

```java
public final class ResponseBuilder {
    private ResponseBuilder() {}
}
```

**Purpose:**

* Declared `final` → prevents subclassing.
* Private constructor → prevents instantiation (utility class).
* All methods are `static` → used without creating an object.

**Impact:** Encourages **static utility usage** for response creation across all controllers and services.

---

## 2. Success Response Methods

### 2.1 `ok(String message, T data)`

```java
public static <T> ResponseEntity<StandardResponse<T>> ok(String message, T data) {
    return build(HttpStatus.OK, message, data);
}
```

**Purpose:**

* Creates a **200 OK** HTTP response.
* Typically used for successful **GET** or **PUT** operations that return data.

**Parameters:**

* `message` → human-readable string describing the result.
* `data` → the actual payload returned to the client.

**Impact:**

* Provides **clear success communication**.
* Standardizes responses for all 200-level operations.

**Example Usage:**

```java
return ResponseBuilder.ok("Customer retrieved successfully", customerDto);
```

---

### 2.2 `created(String message, T data)`

```java
public static <T> ResponseEntity<StandardResponse<T>> created(String message, T data) {
    return build(HttpStatus.CREATED, message, data);
}
```

**Purpose:**

* Creates a **201 CREATED** HTTP response.
* Typically used after **POST** requests when a new resource is created.

**Impact:**

* Communicates to clients that a new resource was successfully created.
* Keeps response structure consistent with `StandardResponse<T>`.

**Example Usage:**

```java
return ResponseBuilder.created("Customer saved successfully", savedCustomerDto);
```

---

## 3. Error Response Methods

### 3.1 `notFound(String message)`

```java
public static <T> ResponseEntity<StandardResponse<T>> notFound(String message) {
    return build(HttpStatus.NOT_FOUND, message, null);
}
```

**Purpose:**

* Creates a **404 NOT FOUND** response.
* Used when a requested resource cannot be located (e.g., customer ID not found).

**Impact:**

* Standardizes **404 responses** across all controllers.
* Automatically sets `data = null` because there’s no payload.

**Example Usage:**

```java
return ResponseBuilder.notFound("Customer not found");
```

---

### 3.2 `error(HttpStatus status, String message, T data)`

```java
public static <T> ResponseEntity<StandardResponse<T>> error(
        HttpStatus status,
        String message,
        T data
) {
    if (status.is2xxSuccessful()) {
        throw new IllegalArgumentException("Error status must be 4xx or 5xx");
    }
    return build(status, message, data);
}
```

**Purpose:**

* Creates **error responses** for **client errors (4xx)** or **server errors (5xx)**.
* Flexible for various error scenarios (validation error, conflict, internal server error).

**Functionality:**

* Checks that `status` is **not 2xx** → ensures logical correctness.
* Calls `build()` to create a standardized `StandardResponse<T>`.

**Impact:**

* Prevents accidental misuse of HTTP success codes for errors.
* Centralizes error response handling, making APIs predictable.

**Example Usage:**

```java
return ResponseBuilder.error(HttpStatus.INTERNAL_SERVER_ERROR, "Database unavailable", null);
```

---

## 4. Core Private Builder

```java
private static <T> ResponseEntity<StandardResponse<T>> build(
        HttpStatus status,
        String message,
        T data
) {
    return ResponseEntity.status(status)
            .body(new StandardResponse<>(status.value(), message, data));
}
```

**Purpose:**

* The **internal method** that actually constructs the `ResponseEntity<StandardResponse<T>>`.
* All public methods (`ok`, `created`, `notFound`, `error`) delegate to this.

**Functionality:**

1. Sets the HTTP status (`ResponseEntity.status(status)`).
2. Wraps payload in a consistent `StandardResponse<T>` object with fields:

    * `status` → HTTP status code.
    * `message` → human-readable message.
    * `data` → optional payload (nullable for errors).

**Impact:**

* Centralizes construction logic → avoids duplication.
* Ensures **consistent structure** across all responses.
* Makes maintenance easy: any change to the response format only happens here.

---

## 5. Overall Purpose & Impact of `ResponseBuilder`

| Aspect                        | Description                                                                  |
| ----------------------------- | ---------------------------------------------------------------------------- |
| **Purpose**                   | Provide a centralized, consistent way to build API responses in Spring Boot. |
| **Controller Simplification** | Controllers no longer need to manually wrap responses.                       |
| **Consistency**               | Ensures all responses follow `StandardResponse<T>` format.                   |
| **Safety**                    | Prevents accidental misuse of HTTP statuses (e.g., 2xx for errors).          |
| **Readability**               | Method names (`ok`, `created`, `notFound`, `error`) clearly describe intent. |
| **Maintainability**           | Any change to response structure is applied in one place.                    |
| **Scalability**               | Easily extended for new status codes or custom response fields.              |

---

### Example Usage in a Controller

```java
@PostMapping("/save")
public ResponseEntity<StandardResponse<CustomerDTO>> saveCustomer(@RequestBody CustomerDTO customerDTO) {
    CustomerDTO savedCustomer = customerService.saveCustomer(customerDTO);
    return ResponseBuilder.created("Customer saved successfully", savedCustomer);
}

@GetMapping("/{id}")
public ResponseEntity<StandardResponse<CustomerDTO>> getCustomer(@PathVariable int id) {
    CustomerDTO customer = customerService.getById(id);
    return ResponseBuilder.ok("Customer retrieved successfully", customer);
}

@ExceptionHandler(NotFoundException.class)
public ResponseEntity<StandardResponse<String>> handleNotFound(NotFoundException e) {
    return ResponseBuilder.notFound(e.getMessage());
}

@ExceptionHandler(SaveFailedException.class)
public ResponseEntity<StandardResponse<String>> handleSaveFailed(SaveFailedException e) {
    return ResponseBuilder.error(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
}
```

---

✅ **Impact in your project:**

1. Eliminates repetitive `ResponseEntity` boilerplate in every controller.
2. Standardizes all responses (success, error, 404, 500).
3. Improves **readability** for new developers joining the project.
4. Makes API responses **predictable** for frontend / mobile clients.
5. Facilitates **centralized future enhancements** (e.g., adding timestamps or metadata to all responses).

---
