<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="easyui-panel" title="商品详情" data-options="fit:true">
    <form class="itemForm" id="itemAddForm" name="itemAddForm" method="post">
        <table style="width:100%;">
            <tr>
                <td class="label">商品类目：</td>
                <td>
                    <input id="cid" name="cid" style="width:200px;">
                </td>
            </tr>
            <tr>
                <td class="label">商品标题：</td>
                <td>
                    <input class="easyui-textbox" type="text" id="title" name="title"
                           data-options="required:true" style="width:100%">
                </td>
            </tr>
            <tr>
                <td class="label">商品卖点：</td>
                <td>
                    <input class="easyui-textbox" type="text" id="sellPoint" name="sellPoint"
                           data-options="validType:'length[0,150]',multiline:true" style="width:100%;height:60px;">
                </td>
            </tr>
            <tr>
                <td class="label">商品价格：</td>
                <td>
                    <input class="easyui-numberbox" type="text" id="priceView" name="priceView"
                           data-options="required:true,min:0,precision:2">
                    <input type="hidden" id="price" name="price">
                </td>
            </tr>
            <tr>
                <td class="label">商品库存：</td>
                <td>
                    <input class="easyui-numberbox" type="text" id="num" name="num"
                           data-options="required:true,min:0,precision:0">
                </td>
            </tr>
            <tr>
                <td class="label">条形码：</td>
                <td>
                    <input class="easyui-textbox" type="text" id="barcode" name="barcode"
                           data-options="validType:'length[0,30]'">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <!-- 加载编辑器的容器 -->
                    <script id="container" name="content" type="text/plain">商品描述</script>
                </td>
            </tr>

            <tr class="paramsShow" style="display:none;">
                <td class="label">商品规格：</td>
                <td>

                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <button onclick="submitForm()" class="easyui-linkbutton" type="button"
                            data-options="iconCls:'icon-ok'">保存
                    </button>
                    <button onclick="clearForm()" class="easyui-linkbutton" type="button"
                            data-options="iconCls:'icon-undo'">重置
                    </button>
                </td>
            </tr>
        </table>
        <input name="paramData" id="paramData" style="display:none;">
    </form>
</div>
<script>
    //提交表单
    function submitForm() {
        $('#itemAddForm').form('submit',{
            //提交表单到item进行处理
            url: 'item',
            //在表单提交之前触发
            onSubmit:function () {
                //给隐藏域设值属性,并且设值
                $('#price').val($('#priceView').val()*100);
                //做表单校验，表单上所有字段全部校验通过才能返回true，才会提交表单，
                //如果有任意一个字段没有校验通过，返回false，不会提交表单
                return $(this).form('validate');
            },
            //后台处理成功之后的回调函数
            success:function(data){
                if (data>0){
                    $.messager.alert('温馨提示','保存成功!');
                    $("#tab").tabs('close','新增商品');
                    ddshop.addTabs('查询商品','item-list');
                }
            }
        });
    }

    //表单重置
    function clearForm() {
        $('#itemAddForm').form('submit',{
            onSubmit:function () {
                return $(this).form('reset');
            }
        });
    }


    //实例化编辑器
    var ue = UE.getEditor('container',{
            initalFrameHeight:'600'
    });

    //加载商品类目树形下拉框
    $('#cid').combotree({
        url:"itemCats?parentId=0",
        required:true,
        onBeforeExpand:function (node) {
            //获取当前被点击的tree
            var $currentTree = $('#cid').combotree('tree');
            //调用options方法
            var option = $currentTree.tree('options');
            //修改option的url属性
            option.url = 'itemCats?parentId=' + node.id;
        },
        onBeforeSelect:function (node) {
            //判断选中节点是否为叶子节点
            var isLeaf = $('#cid').tree('isLeaf', node.target);
            if(!isLeaf){
                $.messager.alert('警告','请选中最终类别','warning');
                return false;
            }
        }
    });

</script>