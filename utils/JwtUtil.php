<?php

declare(strict_types=1);

namespace xabisa\utils;
use Firebase\JWT\JWT;
use Firebase\JWT\Key;
use xabisa\sys\Config;

final class JwtUtil
{
    private static $instance = null;
    private Config $config;

    private function __construct(Config $config)
    {
        $this->config = $config;
    }

    private static function checkInstance()
    {
        if (self::$instance === null) {
            throw new \RuntimeException('JWT Utility is not initialized. Call getInstance() first.');
        }
    }

    private function getSecretKey(): string
    {
        return $this->config->get("wyze_jwt_key");
    }

    public function encodePayload(array $payload, string $key): string
    {
        return JWT::encode($payload, $key, 'HS256');
    }

    public function decodeToken(string $token, string $key): \stdClass
    {
        return JWT::decode($token, new Key($key, 'HS256'));
    }

    public static function getInstance(Config $config): JwtUtil
    {
        if (self::$instance === null) {
            self::$instance = new JwtUtil($config);
        }
        return self::$instance;
    }

    public static function generateKey(int $length = 32): string
    {
        return bin2hex(openssl_random_pseudo_bytes($length));
    }

    public static function decode(string $token): \stdClass
    {
        self::checkInstance();
        $secretKey = self::$instance->getSecretKey();
        return self::$instance->decodeToken($token, $secretKey);
    }

    public static function encode(array $payload): string
    {
        self::checkInstance();
        $secretKey = static::$instance->getSecretKey();
        return static::$instance->encodePayload($payload, $secretKey);
    }
}
