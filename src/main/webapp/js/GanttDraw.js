function drawGannt(canvas, data) {
    // 清空画布
    canvas.get(0).height = canvas.get(0).height;
    // 创建context对象 2d模式
    var ctx = canvas.get(0).getContext("2d");
    for (var child of data.children) {
        if (child.type == "Label") {
            drawLabel(ctx, child);
        } else if (child.type == "Line") {
            drawLine(ctx, child);
        } else {
            console.log("检测到未知对象" + child);
        }
    }
    

}

// rgb色值转16进制
function colorRGBtoHex(color) {
    var r = color.r;
    var g = color.g;
    var b = color.b;
    var hex = "#" + ((1 << 24) + (r << 16) + (g << 8) + b).toString(16).slice(1);
    return hex;
}

function drawLine(ctx, data) {
    ctx.fillStyle = "#000000";
    ctx.moveTo(data.sx, data.sy);
    ctx.lineTo(data.ex, data.ey);
    ctx.stroke();
}

function drawLabel(ctx, data) {
    var color = colorRGBtoHex(data.c);
    // 画背景
    ctx.fillStyle = color;
    ctx.fillRect(data.x, data.y, data.w, data.h);
    // 画文字 默认黑色 默认10px
    var size = 10
    ctx.fillStyle = "#000000";
    ctx.font= size + "px Arial";
    if(data.h == 0) ctx.fillText(data.t,data.x,data.y + size);
    else ctx.fillText(data.t,data.x,data.y + size / 2 + data.h / 2);
}