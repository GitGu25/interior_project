package org.big.config;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.big.dto.InteriorEstimateStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InteriorEstimateStatusTypeHandler extends BaseTypeHandler<InteriorEstimateStatus> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, InteriorEstimateStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.name());
    }

    @Override
    public InteriorEstimateStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return InteriorEstimateStatus.valueOf(rs.getString(columnName));
    }

    @Override
    public InteriorEstimateStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return InteriorEstimateStatus.valueOf(rs.getString(columnIndex));
    }

    @Override
    public InteriorEstimateStatus getNullableResult(java.sql.CallableStatement cs, int columnIndex) throws SQLException {
        return InteriorEstimateStatus.valueOf(cs.getString(columnIndex));
    }
}
