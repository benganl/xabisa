<?php
/**
 * User: lanton
 * Date: 2024/08/25
 * Time: 7:16 PM
 */

declare(strict_types=1);

namespace wyzetech\shabisa\system;

use \PDO;
use \PDOException;
use wyzetech\shabisa\system\database\QueryParam;

class Database
{
    private static ?Database $instance = null;
    private ?PDO $dbh = null;
    private ?string $username = null;
    private ?string $password = null;

    private function __construct(string $username, string $password)
    {
        $this->username = $username;
        $this->password = $password;
    }

    public function connect()
    {
        try {
            try {
                $this->dbh = new PDO('mysql:host=localhost;dbname=wyzecmsdb;charset=utf8', $this->username, $this->password);
                $this->dbh->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            } catch (PDOException $e) {
                echo $e->getMessage();
            }
        } catch (PDOException $e) {
            throw new Exception('Database connection failed: ' . $e->getMessage());
        }
    }

    public function beginTransaction()
    {
        $this->dbh->beginTransaction();
    }

    public function commit()
    {
        $this->dbh->commit();
    }

    public function rollBack()
    {
        $this->dbh->rollBack();
    }

    public function query(string $sql, $params = null): array
    {
        $result = [];

        $sth = $this->dbh->prepare($sql, array(PDO::ATTR_CURSOR => PDO::CURSOR_SCROLL));
        $sth->execute();
        foreach ($sth->fetch(PDO::FETCH_ASSOC) as $key => $value) {
            $result[$key] = $value;
        }

        return $result;
    }

    public static function getInstance(string $username, string $password)
    {
        if (self::$instance === null) {
            self::$instance = new self($username, $password);
        }
        return self::$instance;
    }
}
