<?php

declare(strict_types=1);

namespace wyzetech\shabisa\system;

use \PDO;
use \PDOStatement;
use \PDOException;
use wyzetech\shabisa\system\database\QueryParamProcessor;

class Database
{
    private const SELECT_STMT_REGEX = '/^\s*SELECT\s+/i';
    private const INSERT_STMT_REGEX = '/^\s*INSERT\s+INTO\s+/i';
    private const UPDATE_STMT_REGEX = '/^\s*UPDATE\s+/i';
    private const DELETE_STMT_REGEX = '/^\s*DELETE\s+FROM\s+/i';

    private static ?Database $instance = null;
    private ?PDO $dbh = null;

    private function __construct()
    {
        // The constructor is private to prevent direct instantiation.
    }

    public static function getInstance(Configuration &$config): self
    {
        if (self::$instance === null) {
            self::$instance = new self();
            self::$instance->connect($config);
        }
        return self::$instance;
    }

    private function connect($config): void
    {
        $driver = $config->db['driver'];
        $host = $config->db['host'];
        $port = $config->db['port'];
        $databaseName = $config->db['dbname'];
        $username = $config->db['username'];
        $password = $config->db['password'];

        if ($this->dbh === null) {
            try {
                $this->dbh = new PDO("$driver:host=$host:$port;dbname=$databaseName;charset=utf8", $username, $password);
                $this->dbh->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            } catch (PDOException $e) {
                throw new Exception('Database connection failed: ' . $e->getMessage());
            }
        }
    }

    public function beginTransaction(): bool
    {
        return $this->dbh?->beginTransaction();
    }

    public function commit(): bool
    {
        return $this->dbh?->commit();
    }

    public function rollBack(): bool
    {
        return $this->dbh?->rollBack();
    }

    /**
     * Executes a SQL query and returns the result based on the type of query.
     *
     * @param string $sql The SQL query to execute.
     * @param array|null $params Parameters to bind to the query.
     * @return mixed The result of the query execution.
     */
    protected function execute(string $sql, ?array $params = null, bool $isDelegate = false): array|bool|string|int|PDOStatement
    {
        $result = null;
        if ($this->dbh === null) {
            throw new Exception("Failed to obtain database handle!");
        }

        try {
            $sth = $this->dbh->prepare($sql);
            $processor = new QueryParamProcessor($params);
            $processor->bindParams($sth);
            $sth->execute();

            if ($isDelegate) {
                $result = $sth;
            } elseif (preg_match(self::SELECT_STMT_REGEX, $sql)) {
                $result = $sth->fetchAll(PDO::FETCH_ASSOC);
            } elseif (preg_match(self::INSERT_STMT_REGEX, $sql)) {
                $result = $this->dbh->lastInsertId();
            } elseif (preg_match(self::UPDATE_STMT_REGEX, $sql) || preg_match(self::DELETE_STMT_REGEX, $sql)) {
                $result = $sth->rowCount();
            }

            if ($result === null) {
                throw new Exception("Could not execute query");
            }

            return $result;
        } catch (PDOException $e) {
            throw new Exception('Query failed: ' . $e->getMessage());
        }
    }

    public function insert(string $sql, ?array $params = null): int
    {
        return $this->execute($sql, $params, isDelegate: true)->lastInsertId();
    }

    public function select(string $sql, ?array $params = null): array
    {
        return $this->execute($sql, $params, isDelegate: true)->fetchAll(PDO::FETCH_ASSOC);
    }

    public function update(string $sql, ?array $params): int
    {
        return $this->execute($sql, $params, isDelegate: true)->rowCount();
    }

    public function delete(string $sql, ?array $params): int
    {
        return $this->execute($sql, $params, isDelegate: true)->rowCount();
    }

    public function __destruct()
    {
        $this->dbh = null;
    }
}
