package ru.weierstrass;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import ru.weierstrass.db.JdbcQuery;
import ru.weierstrass.fixture.FixtureAlreadyLoadedException;
import ru.weierstrass.fixture.FixtureLoadingException;
import ru.weierstrass.pgsql.PgSQLFixture;
import ru.weierstrass.pgsql.PgSQLFixtureLoader;
import ru.weierstrass.pgsql.PgSQLFixtureSequence;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@Slf4j
public class PgSQLFixtureLoaderTest {

    private class JdbcQueryProxy implements JdbcQuery {
        private List<String> queries = new ArrayList<>();
        private List<Object[]> params = new ArrayList<>();

        @Override
        public void insert( String query, Object... params ) {
            queries.add( query );
            this.params.add( params );
        }

        @Override
        public void execute( String query, Object... params ) {
            queries.add( query );
            this.params.add( params );
        }

        public String getQuery( int i ) {
            return queries.get( i );
        }

        public Object[] getParams( int i ) {
            return params.get( i );
        }

    }

    private JdbcQueryProxy jdbc = new JdbcQueryProxy();

    @Test
    public void testPgSqlLoader() throws FixtureLoadingException {
        PgSQLFixtureSequence sequence = mock( PgSQLFixtureSequence.class );
        when( sequence.getName() ).thenReturn( "test_table_seq" );
        when( sequence.getTableName() ).thenReturn( "test_table" );
        when( sequence.getInitialValue() ).thenReturn( 1 );
        when( sequence.getSequenceColumnName() ).thenReturn( "id" );

        PgSQLFixture fixture = mock( PgSQLFixture.class );
        when( fixture.getTableName() ).thenReturn( "test_table" );
        when( fixture.getSequences() ).thenReturn( new ArrayList<PgSQLFixtureSequence>() {{
            add( sequence );
        }} );
        when( fixture.getData() ).thenReturn( new ArrayList<Map<String, Object>>() {{
            add( new HashMap<String, Object>() {{
                put( "id", 1 );
                put( "text", "abc" );
            }} );
            add( new HashMap<String, Object>() {{
                put( "id", 2 );
                put( "text", "def" );
            }} );
            add( new HashMap<String, Object>() {{
                put( "id", 3 );
                put( "text", "ghi" );
            }} );
        }} );

        PgSQLFixtureLoader loader = new PgSQLFixtureLoader( jdbc );

        loader.load( fixture );

        assertTrue( jdbc.getQuery( 0 ).equals( "INSERT INTO test_table (\"id\",\"text\") VALUES (?,?)" ) );
        assertTrue( Arrays.equals( jdbc.getParams( 0 ), new Object[]{ 1, "abc" } ) );

        assertTrue( jdbc.getQuery( 1 ).equals( "INSERT INTO test_table (\"id\",\"text\") VALUES (?,?)" ) );
        assertTrue( Arrays.equals( jdbc.getParams( 1 ), new Object[]{ 2, "def" } ) );

        assertTrue( jdbc.getQuery( 2 ).equals( "INSERT INTO test_table (\"id\",\"text\") VALUES (?,?)" ) );
        assertTrue( Arrays.equals( jdbc.getParams( 2 ), new Object[]{ 3, "ghi" } ) );

        assertTrue( jdbc.getQuery( 3 ).equals( "SELECT SETVAL( 'test_table_seq', 1 ) FROM test_table" ) );
    }

    @Test( expected = FixtureAlreadyLoadedException.class )
    public void testPgSQLLoaderDuplicate() throws FixtureLoadingException {
        PgSQLFixture fixture = mock( PgSQLFixture.class );
        when( fixture.getTableName() ).thenReturn( "test_table" );
        when( fixture.getData() ).thenReturn( new ArrayList<>() );

        PgSQLFixtureLoader loader = new PgSQLFixtureLoader( jdbc );

        loader.load( fixture );
        loader.load( fixture );

    }

}
