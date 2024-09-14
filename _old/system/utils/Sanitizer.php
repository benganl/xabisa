<?php

declare(strict_types=1);

namespace wyzetech\shabisa\system\utils;

class Sanitizer
{
    private function __construct()
    {
        // constructor here

    }

    /**
     * Sanitize input recursively for both strings and arrays.
     */
    public static function sanitize($data)
    {
        if (is_array($data)) {
            foreach ($data as $key => $value) {
                $data[self::sanitize($key)] = self::sanitize($value);
            }
        } else {
            $data = self::sanitizeString($data);
        }

        return $data;
    }

    /**
     * Sanitize a string.
     */
    public static function sanitizeString(string $data): string
    {
        // Add sanitization logic, e.g., htmlspecialchars, trim, etc.
        $data = trim($data);
        $data = stripslashes($data);
        $data = htmlspecialchars($data);
        return $data;
    }
}
