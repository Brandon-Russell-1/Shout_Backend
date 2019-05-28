CREATE FUNCTION delete_old_rows() RETURNS trigger
    AS $$
BEGIN
  DELETE FROM shout WHERE shout_date < NOW() - INTERVAL '2 days';
  RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_delete_old_rows
    AFTER INSERT ON shout
    EXECUTE PROCEDURE delete_old_rows();