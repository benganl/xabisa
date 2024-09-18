<?php

declare(strict_types=1);

namespace xabisa\app\user\model;
use xabisa\sys\Model;

class User extends Model
{
    private string $username;
    private string $password;
    private string $email;
    private string $mobileNumber;

    public function __construct(string $id = null,
        string $username = null,
        string $password = null,
        $email = null,
        $mobileNumber = null)
    {
        $this->id = $id;
        $this->username = $username;
        $this->password = $password;
        $this->email = $email;
        $this->mobileNumber = $mobileNumber;
    }

    public function getUsername(): string
    {
        return $this->username;
    }

    public function getPassword(): string
    {
        return $this->password;
    }

    public function getEmail(): string
    {
        return $this->email;
    }

    public function getMobileNumber(): ?string
    {
        return $this->mobileNumber;
    }

    public function __clone()
    {
        $user = new User();

        $user->id = $this->id;
        $user->username = $this->username;
        $user->password = $this->password;
        $user->email = $this->email;
        $user->mobileNumber = $this->mobileNumber;
        
        return $user;
    }
}
