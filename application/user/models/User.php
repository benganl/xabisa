<?php
/**

 * User: lanton
 * Date: 2024/08/25
 * Time: 7:46 PM
 */

declare(strict_types=1);

namespace wyzetech\shabisa\application\user\models;

class User {
    private $mobileNumber = null;
    private $emailAddress = null;
    private $password = null;

    public function __construct(string $mobileNumber, string $emailAddress, string $password)
    {
        $this->mobileNumber = $mobileNumber;
        $this->emailAddress = $emailAddress;
        $this->password = $password;
    }

    public function __toString()
    {
        return "[mobileNumber: $this->mobileNumber, emailAddress: $this->emailAddress, password: {*** REDACTED ***}]";
    }
}