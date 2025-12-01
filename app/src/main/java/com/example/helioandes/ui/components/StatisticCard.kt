package com.example.helioandes.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StatisticCard(
    value: String,
    label: String,
    unit: String = "",
    backgroundColor: Color = Color.White,
    textColor: Color = Color(0xFF1A1A2E),
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = value,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
            if (unit.isNotEmpty()) {
                Text(
                    text = unit,
                    fontSize = 14.sp,
                    color = textColor.copy(alpha = 0.7f),
                    modifier = Modifier.padding(start = 4.dp, bottom = 2.dp)
                )
            }
        }
        
        Text(
            text = label,
            fontSize = 12.sp,
            color = textColor.copy(alpha = 0.8f),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun StatisticRow(
    statistics: List<StatisticData>
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        statistics.forEach { stat ->
            StatisticCard(
                value = stat.value,
                label = stat.label,
                unit = stat.unit,
                backgroundColor = stat.backgroundColor,
                textColor = stat.textColor,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

data class StatisticData(
    val value: String,
    val label: String,
    val unit: String = "",
    val backgroundColor: Color = Color.White,
    val textColor: Color = Color(0xFF1A1A2E)
)