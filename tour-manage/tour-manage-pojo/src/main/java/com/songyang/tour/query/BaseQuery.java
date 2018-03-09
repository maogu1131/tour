package com.songyang.tour.query;/**
 * Created by lenovo on 2017/9/1.
 */

import com.songyang.tour.enums.SortColumn;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;

/**
 * 基础查询
 *
 * @author
 * @create 2017-09-01 11:06
 **/
public class BaseQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 排序
     */
    private List<SortColumn> sorts;

    /**
     * 记录开始位置
     */
    private Integer offset;

    /**
     * 记录行数
     */
    private Integer rows;

    public List<SortColumn> getSorts() {
        return sorts;
    }

    public void setSorts(List<SortColumn> sorts) {
        this.sorts = sorts;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public void checkBaseQuery(int maxRows) {
        Assert.notNull(this.getOffset(), "查询偏移量不能为空");
        Assert.notNull(this.getRows(), "查询记录数量不能为空");
        Assert.isTrue(this.getOffset() >= 0);
        Assert.isTrue(this.getRows() > 0, "查询记录数量必须大于0");
        Assert.isTrue(this.getRows() <= maxRows, "查询记录数量不能超过" + maxRows);
    }


}
