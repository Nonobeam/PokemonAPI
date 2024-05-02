# JWT

## JSON example:

```json
{
  "header": {
    "alg": "HS256",
    "typ": "JWT"
  },
  "payload": {
    // Registered claims
    "iss": "https://example.com", // Issuer
    // Sub typically contains a unique identifier (such as a user ID) for the entity (user) associated with the token.
    "sub": "1234567890",          // Subject
    "aud": "https://example.org", // Audience
    "exp": 1300819380,            // Expiration Time
    "nbf": 1300819300,            // Not Before
    "iat": 1300819320,            // Issued At
    "jti": "id123456",            // JWT ID

    // Public claims
    "name": "John Doe",
    "admin": true,

    // Private claims
    "user_id": "usr_001",
    "permissions": ["read", "write", "delete"]
  },
  "signature": "HMACSHA256(
    base64UrlEncode(header) + '.' +
    base64UrlEncode(payload),
    your-256-bit-secret
  )"
}
